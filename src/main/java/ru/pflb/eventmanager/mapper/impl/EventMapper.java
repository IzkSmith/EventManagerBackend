package ru.pflb.eventmanager.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;

@Component
@Mapper(entity = Event.class, dto = EventDto.class)
public class EventMapper extends AbstractMapper<Event, EventDto> {
}
