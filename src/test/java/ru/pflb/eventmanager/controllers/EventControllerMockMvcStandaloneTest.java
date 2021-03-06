package ru.pflb.eventmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.service.EventService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerMockMvcStandaloneTest {
    private MockMvc mvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private ru.pflb.eventmanager.controller.EventController eventController;

    private JacksonTester<EventDto> jsonEvent;

    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(eventController)
//                .setControllerAdvice(new EventExceptionHandler())
                .addFilters(new ru.pflb.eventmanager.controller.Filter.EventFilter())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        EventDto dto = new EventDto();
        dto.setId(1L);
        dto.setName("test");

        given(eventService.get(1L)).willReturn(dto);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:8080/api/v1/event/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonEvent.write(dto).getJson()
        );
    }

    @Test
    public void canCreateANewEvent() throws Exception {
        EventDto dto = new EventDto();
        dto.setId(1L);
        dto.setName("Евент");
        dto.setCityId(1L);
        dto.setHolderId(6L);
        dto.setDescription("adsa");
        dto.setMaxMembers(25);
        dto.setDate("2019-12-08 12:15:41");
        dto.setContacts("wasdas@gmail.com");

        // when
        MockHttpServletResponse response = mvc.perform(
                post("http://localhost:8080/api/v1/event").contentType(MediaType.APPLICATION_JSON).content(
                        jsonEvent.write(dto).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
