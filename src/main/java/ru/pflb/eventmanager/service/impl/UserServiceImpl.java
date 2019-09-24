package ru.pflb.eventmanager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.exception.DataBaseException;
import ru.pflb.eventmanager.mapper.impl.UserMapper;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.UserService;

import java.util.stream.Collectors;

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
    public UserDto create(UserDto dto) throws JsonProcessingException {
        UserDto userDto = mapper.toDto(repository.save(mapper.toEntity(dto)));
        log.info("New User saved: {}", new ObjectMapper().writeValueAsString(userDto));
        return userDto;
    }

    @Override
    public UserDto update(UserDto dto) throws JsonProcessingException {
        User user = repository.findById(dto.getId())
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", dto.getId())));
        UserDto userDto = mapper.toDto(repository.save(mapper.toEntity(dto, user)));
        log.info("New User saved: {}", new ObjectMapper().writeValueAsString(userDto));
        return userDto;
    }

    @Override
    public UserDto get(Long id){
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", id))));
    }

    @Override
    public UserDto getByCredentials(String username, String password) {
        return mapper.toDto(repository.getByUsernameAndPassword(username, password)
                .orElseThrow(() -> new DataBaseException("Неправильная пара логин-пароль.")));
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return new PageImpl<>(
                users.getContent()
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()),
                pageable,
                users.getTotalElements()
        );
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        boolean deleted = !repository.findById(id).isPresent();
        if (deleted) {
            log.info("User deleted by ID: {}", id);
        }
        return deleted;
    }
}
