package ru.pflb.eventmanager.mapper.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;
import ru.pflb.eventmanager.repository.CityRepository;
import ru.pflb.eventmanager.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Mapper(entity = Event.class, dto = EventDto.class)
public class EventMapper extends AbstractMapper<Event, EventDto> {
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventMapper( CityRepository cityRepository, UserRepository userRepository) {
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Event.class, EventDto.class)
                .addMappings(m -> m.skip(EventDto::setUserIds)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(EventDto::setCityId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(EventDto.class, Event.class)
                .addMappings(m -> m.skip(Event::setUsers)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Event::setCity)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Event source, EventDto destination) {
        if (source.getUsers() != null) {
            destination.getUserIds().addAll(source.getUsers().stream().map(User::getId).collect(Collectors.toList()));
        }
        destination.setCityId(getId(source));
    }
    private Long getId(Event source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCity().getId();
    }

    @Override
    protected void mapSpecificFields(EventDto source, Event destination) {
        if (source.getUserIds() != null) {
            destination.getUsers().addAll(
                    source.getUserIds()
                            .stream()
                            .map(l -> userRepository.findById(l).orElse(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())
            );
        }
        destination.setCity(cityRepository.findById(source.getCityId()).orElse(null));
    }
}
