package ru.pflb.eventmanager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pflb.eventmanager.dto.UserDTO;
import ru.pflb.eventmanager.exception.DataBaseException;
import ru.pflb.eventmanager.mapper.impl.UserMapper;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository ;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository,
                           UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO create(UserDTO dto) throws JsonProcessingException {
        UserDTO userDto = mapper.toDto(repository.save(mapper.toEntity(dto)));
        log.info("New User saved: {}", new ObjectMapper().writeValueAsString(userDto));
        return userDto;
    }

    @Override
    public UserDTO get(Long id){
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", id))));
    }

    @Override
    public UserDTO getByCredentials(String username, String password) {
        return mapper.toDto(repository.getByUsernameAndPassword(username, password)
                .orElseThrow(() -> new DataBaseException("Неправильная пара логин-пароль.")));
    }
}
