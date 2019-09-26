package ru.pflb.eventmanager.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.exception.DataBaseException;
import ru.pflb.eventmanager.mapper.impl.EventMapper;
import ru.pflb.eventmanager.repository.EventRepository;
import ru.pflb.eventmanager.service.EventService;

import java.util.stream.Collectors;

@Slf4j
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository repository ;
    private final EventMapper mapper;

    public EventServiceImpl(EventRepository repository,
                           EventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EventDto create(EventDto dto) throws JsonProcessingException {
        EventDto eventDto = mapper.toDto(repository.save(mapper.toEntity(dto)));
        log.info("New Event saved: {}", new ObjectMapper().writeValueAsString(eventDto));
        return eventDto;
    }

    @Override
    public EventDto update(EventDto dto) throws JsonProcessingException {
        Event event = repository.findById(dto.getId())
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", dto.getId())));
        EventDto eventDTO = mapper.toDto(repository.save(mapper.toEntity(dto, event)));
        log.info("New Event saved: {}", new ObjectMapper().writeValueAsString(eventDTO));
        return eventDTO;
    }

    @Override
    public EventDto get(Long id){
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", id))));
    }

    @Override
    public Page<EventDto> getAll(Pageable pageable) {
        Page<Event> events = repository.findAll(pageable);
        return new PageImpl<>(
                events.getContent()
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()),
                pageable,
                events.getTotalElements()
        );
    }


    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        boolean deleted = !repository.findById(id).isPresent();
        if (deleted) {
            log.info("Event deleted by ID: {}", id);
        }
        return deleted;
    }
}
