package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.pflb.eventmanager.dto.UserDTO;
import ru.pflb.eventmanager.entity.User;

public interface UserService {
    UserDTO create(UserDTO dto) throws JsonProcessingException;

    UserDTO getByCredentials(String username, String login);

    UserDTO get(Long id);
}
