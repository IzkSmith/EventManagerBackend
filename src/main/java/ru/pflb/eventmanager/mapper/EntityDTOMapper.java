package ru.pflb.eventmanager.mapper;

import ru.pflb.eventmanager.dto.AbstractDto;
import ru.pflb.eventmanager.entity.AbstractEntity;

public interface EntityDTOMapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    E toEntity(D dto, E entity);

    D toDto(E entity);

    D toDto(E entity, D dto);
}
