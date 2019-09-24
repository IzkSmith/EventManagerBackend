package ru.pflb.eventmanager.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.EventDTO;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;

@Component
@Mapper(entity = Event.class, dto = EventDTO.class)
public class EventMapper extends AbstractMapper<Event, EventDTO> {
}
