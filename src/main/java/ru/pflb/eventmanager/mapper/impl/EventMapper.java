package ru.pflb.eventmanager.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;
import ru.pflb.eventmanager.repository.CityRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@Mapper(entity = Event.class, dto = EventDto.class)
public class EventMapper extends AbstractMapper<Event, EventDto> {
    private final CityRepository cityRepository;

    @Autowired
    public EventMapper( CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Event.class, EventDto.class)
                .addMappings(m -> m.skip(EventDto::setCityId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(EventDto.class, Event.class)
                .addMappings(m -> m.skip(Event::setCity)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Event source, EventDto destination) {
        destination.setCityId(getId(source));
    }
    private Long getId(Event source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCity().getId();
    }

    @Override
    protected void mapSpecificFields(EventDto source, Event destination) {
        destination.setCity(cityRepository.findById(source.getCityId()).orElse(null));
    }
}
