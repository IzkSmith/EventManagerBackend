package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.pflb.eventmanager.dto.CityDto;

public interface CityService {
    CityDto create(CityDto dto) throws JsonProcessingException;

    CityDto update(CityDto dto) throws JsonProcessingException;

    CityDto get(Long id);

    Page<CityDto> getAll(Pageable pageable);

    boolean delete(Long id);
}
