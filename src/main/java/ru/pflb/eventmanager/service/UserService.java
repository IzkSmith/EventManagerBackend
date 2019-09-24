package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.pflb.eventmanager.dto.UserDto;


public interface UserService {
    UserDto create(UserDto dto) throws JsonProcessingException;

    UserDto update(UserDto dto) throws JsonProcessingException;

    UserDto get(Long id);

    UserDto getByCredentials(String username, String login);

    Page<UserDto> getAll(Pageable pageable);

    boolean delete(Long id);
}
