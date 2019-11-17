package ru.pflb.eventmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pflb.eventmanager.controller.CityController;
import ru.pflb.eventmanager.controller.Filter.CityFilter;
import ru.pflb.eventmanager.dto.CityDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import ru.pflb.eventmanager.service.CityService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
//                .setControllerAdvice(new CityExceptionHandler())
                .addFilters(new CityFilter())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        CityDto dto = new CityDto();
        dto.setId(1L);
        dto.setName("test");

        given(cityService.get(1L)).willReturn(dto);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:8080/api/v1/city/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCity.write(dto).getJson()
        );
    }

    @Test
    public void canCreateANewCity() throws Exception {
        CityDto dto = new CityDto();
        dto.setName("Саранск");

        // when
        MockHttpServletResponse response = mvc.perform(
                post("http://localhost:8080/api/v1/city").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCity.write(dto).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
