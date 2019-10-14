package ru.pflb.eventmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pflb.eventmanager.dto.CityDto;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.entity.City;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.exception.NonExistingCityException;
import ru.pflb.eventmanager.mapper.impl.CityMapper;
import ru.pflb.eventmanager.repository.CityRepository;
import ru.pflb.eventmanager.rest.controller.CityController;
import ru.pflb.eventmanager.rest.controller.CityFilter;
import ru.pflb.eventmanager.rest.controller.ExceptionHandler.CityExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pflb.eventmanager.service.CityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CityControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    private JacksonTester<CityDto> jsonCity;

    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(cityController)
                .setControllerAdvice(new CityExceptionHandler())
                .addFilters(new CityFilter())
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
        CityDto dto = new CityDto();
        dto.setId(l);
        dto.setName("Тула");
        dto.setEvents(events);

        given(cityService.get(l))
                .willReturn(dto);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:8080/api/v1/city/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCity.write(dto).getJson()
        );
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        long l = 2;
        given(cityService.get(l)).willThrow(new NonExistingCityException());

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:8080/api/v1/city/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }
}
