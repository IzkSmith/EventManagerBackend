package ru.pflb.eventmanager.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;

@Component
@Mapper(entity = User.class, dto = UserDto.class)
public class UserMapper extends AbstractMapper<User, UserDto> {
}