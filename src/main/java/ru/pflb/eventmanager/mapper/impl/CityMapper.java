package ru.pflb.eventmanager.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.CityDto;
import ru.pflb.eventmanager.entity.City;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;

@Component
@Mapper(entity = City.class, dto = CityDto.class)
public class CityMapper extends AbstractMapper<City, CityDto> {
}