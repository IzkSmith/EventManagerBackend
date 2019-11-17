package ru.pflb.eventmanager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pflb.eventmanager.dto.RoleDto;
import ru.pflb.eventmanager.entity.Role;
import ru.pflb.eventmanager.exception.DataBaseException;
import ru.pflb.eventmanager.mapper.impl.RoleMapper;
import ru.pflb.eventmanager.repository.RoleRepository;
import ru.pflb.eventmanager.service.RoleService;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository ;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository repository,
                            RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RoleDto create(RoleDto dto) throws JsonProcessingException {
        RoleDto roleDto = mapper.toDto(repository.save(mapper.toEntity(dto)));
        log.info("New Role saved: {}", new ObjectMapper().writeValueAsString(roleDto));
        return roleDto;
    }

    @Override
    public RoleDto update(RoleDto dto) throws JsonProcessingException {
        Role role = repository.findById(dto.getId())
                .orElseThrow(() -> new DataBaseException(String.format("Роль с ID %d не существует.", dto.getId())));
        RoleDto roleDTO = mapper.toDto(repository.save(mapper.toEntity(dto, role)));
        log.info("New Role saved: {}", new ObjectMapper().writeValueAsString(roleDTO));
        return roleDTO;
    }

    @Override
    public RoleDto get(Long id){
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Роль с ID %d не существует.", id))));
    }

    @Override
    public Page<RoleDto> getAll(Pageable pageable) {
        Page<Role> roles = repository.findAll(pageable);
        return new PageImpl<>(
                roles.getContent()
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()),
                pageable,
                roles.getTotalElements()
        );
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        boolean deleted = repository.findById(id).isEmpty();
        if (deleted) {
            log.info("Role deleted by ID: {}", id);
        }
        return deleted;
    }
}
