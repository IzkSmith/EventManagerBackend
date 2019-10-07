package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.pflb.eventmanager.dto.CityDto;
import ru.pflb.eventmanager.service.impl.CityServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CityServiceImplTest {

    @Autowired
    private CityServiceImpl cityService;

    @Test
    public void getAllTest()  {
        List<CityDto> cities = generateListNewView(10);
        cities.forEach(n -> {
            try {
                cityService.create(n);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });
        Page<CityDto> cityDtoPage = cityService.getAll(PageRequest.of(0, 10));

        assertEquals(cities.size(), cityDtoPage.getContent().size());
    }

    private List<CityDto> generateListNewView(int count) {
        List<CityDto> newsViews = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            newsViews.add(generateCityDto(new Random().nextInt()));
        }

        return newsViews;
    }

    private CityDto generateCityDto(int nextInt) {
        CityDto cityDto = new CityDto();
        String randomString = UUID.randomUUID().toString();

        cityDto.setName("City of "+ randomString.substring(2,12));
        return cityDto;
    }
}
