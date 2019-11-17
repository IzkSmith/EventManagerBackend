package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.dto.CityDto;
import ru.pflb.eventmanager.entity.City;
import ru.pflb.eventmanager.mapper.impl.CityMapper;
import ru.pflb.eventmanager.repository.CityRepository;
import ru.pflb.eventmanager.service.impl.CityServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CityServiceImplIntegrationTest {

    @TestConfiguration
    static class CityServiceImplTestContextConfiguration {

        private CityRepository cityRepository;

        private CityMapper mapper;

        @Bean
        public CityService cityService() {
            return  new CityServiceImpl(cityRepository, mapper );
        }
    }

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTest() {
        City moscow = new City();
        moscow.setName("Alex");

        CityDto moscowDto = new CityDto();
        moscow.setName("Alex");

        when(repository.save(moscow)).thenReturn(moscow);//фейковый репозиторий

        try {
            assertThat(cityService.create(moscowDto)).isEqualTo(moscow);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllTest() {
        City moscow = new City();
        moscow.setId(1L);
        moscow.setName("Alex");
        City moscow2 = new City();
        moscow.setId(2L);
        moscow.setName("Alex");
        List<City> cities = new ArrayList<>();
        cities.add(moscow);
        cities.add(moscow2);
        when(repository.findAll()).thenReturn(cities);

        Pageable pageable = PageRequest.of(0, 2);
        assertThat(2).isEqualTo(cityService.getAll(pageable).getSize());
    }

    @Test
    public void getTest() {
        City moscow = new City();
        moscow.setId(1L);
        moscow.setName("Alex");

        assertThat(cityService.get(moscow.getId())).isEqualTo(moscow);
    }
}
