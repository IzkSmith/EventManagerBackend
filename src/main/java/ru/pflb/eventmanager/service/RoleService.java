package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.pflb.eventmanager.dto.RoleDto;

public interface RoleService {

    RoleDto create(RoleDto dto) throws JsonProcessingException;

    RoleDto update(RoleDto dto) throws JsonProcessingException;

    RoleDto get(Long id);

    Page<RoleDto> getAll(Pageable pageable);

    boolean delete(Long id);
}
