package ru.pflb.eventmanager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pflb.eventmanager.dto.CityDto;
import ru.pflb.eventmanager.entity.City;
import ru.pflb.eventmanager.exception.DataBaseException;
import ru.pflb.eventmanager.mapper.impl.CityMapper;
import ru.pflb.eventmanager.repository.CityRepository;
import ru.pflb.eventmanager.service.CityService;

import java.util.stream.Collectors;


@Slf4j
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository ;
    private final CityMapper mapper;

    public CityServiceImpl(CityRepository repository,
                           CityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CityDto create(CityDto dto) throws JsonProcessingException {
        CityDto cityDto = mapper.toDto(repository.save(mapper.toEntity(dto)));
        log.info("New City saved: {}", new ObjectMapper().writeValueAsString(cityDto));
        return cityDto;
    }

    @Override
    public CityDto update(CityDto dto) throws JsonProcessingException {
        City city = repository.findById(dto.getId())
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", dto.getId())));
        CityDto cityDto = mapper.toDto(repository.save(mapper.toEntity(dto, city)));
        log.info("New City saved: {}", new ObjectMapper().writeValueAsString(cityDto));
        return cityDto;
    }

    @Override
    public CityDto get(Long id){
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", id))));
    }

    @Override
    public Page<CityDto> getAll(Pageable pageable) {
        Page<City> cities = repository.findAll(pageable);
        return new PageImpl<>(
                cities.getContent()
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()),
                pageable,
                cities.getTotalElements()
        );
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        boolean deleted = !repository.findById(id).isPresent();
        if (deleted) {
            log.info("City deleted by ID: {}", id);
        }
        return deleted;
    }
}
