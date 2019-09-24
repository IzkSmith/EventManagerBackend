package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.pflb.eventmanager.dto.EventDto;

public interface EventService {
    EventDto create(EventDto dto) throws JsonProcessingException;

    EventDto update(EventDto dto) throws JsonProcessingException;

    EventDto get(Long id);

    Page<EventDto> getAll(Pageable pageable);

    boolean delete(Long id);
}
