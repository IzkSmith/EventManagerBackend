package ru.pflb.eventmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pflb.eventmanager.controller.ExceptionHandler.UserExceptionHandler;
import ru.pflb.eventmanager.controller.Filter.UserFilter;
import ru.pflb.eventmanager.controller.UserController;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import ru.pflb.eventmanager.service.UserService;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerMockMvcStandaloneTest {
    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private JacksonTester<UserDto> jsonUser;

    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new UserExceptionHandler())
                .addFilters(new UserFilter())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        long l=2;
        List<EventDto> events= new ArrayList<>();
        EventDto event = new EventDto();
        event.setId(l);
        event.setName("Concert");
        event.setCityId(l);
        event.setDate("2017-09-17 18:47:52.69");
        event.setMaxMembers(500);
        event.setDescription("There will be a concert.");
        events.add(event);
        UserDto dto = new UserDto();
        dto.setId(l);
        dto.setEvents(events);

        given(userService.get(l))
                .willReturn(dto);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:8080/api/v1/event/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(dto).getJson()
        );
    }

    @Test
    public void canCreateANewUser() throws Exception {
        UserDto dto = new UserDto();

        // when
        MockHttpServletResponse response = mvc.perform(
                post("http://localhost:8080/api/v1/event").contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(dto).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
