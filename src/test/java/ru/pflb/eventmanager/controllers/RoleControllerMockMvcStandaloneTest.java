package ru.pflb.eventmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pflb.eventmanager.controller.ExceptionHandler.RoleExceptionHandler;
import ru.pflb.eventmanager.controller.Filter.RoleFilter;
import ru.pflb.eventmanager.controller.RoleController;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.dto.RoleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import ru.pflb.eventmanager.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RoleControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    private JacksonTester<RoleDto> jsonRole;

    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(roleController)
                .setControllerAdvice(new RoleExceptionHandler())
                .addFilters(new RoleFilter())
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
        RoleDto dto = new RoleDto();
        dto.setId(l);

        given(roleService.get(l))
                .willReturn(dto);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:8080/api/v1/role/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonRole.write(dto).getJson()
        );
    }

    @Test
    public void canCreateANewRole() throws Exception {
        RoleDto dto = new RoleDto();

        // when
        MockHttpServletResponse response = mvc.perform(
                post("http://localhost:8080/api/v1/role").contentType(MediaType.APPLICATION_JSON).content(
                        jsonRole.write(dto).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}