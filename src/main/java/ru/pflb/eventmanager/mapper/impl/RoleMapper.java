package ru.pflb.eventmanager.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.RoleDto;
import ru.pflb.eventmanager.entity.Role;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;

@Component
@Mapper(entity = Role.class, dto = RoleDto.class)
public class RoleMapper extends AbstractMapper<Role, RoleDto> {

}