package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.entity.User;

public interface UserService {

    User register(User user);

    User findByUsername(String username);

    UserDto create(UserDto dto);

    UserDto update(UserDto dto) throws JsonProcessingException;

    UserDto get(Long id);

    Page<UserDto> getAll(Pageable pageable);

    boolean delete(Long id);
}
