package ru.pflb.eventmanager.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.UserDTO;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;

@Component
@Mapper(entity = User.class, dto = UserDTO.class)
public class UserMapper extends AbstractMapper<User, UserDTO> {
}