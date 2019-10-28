package ru.pflb.eventmanager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.entity.Role;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.enumeration.Status;
import ru.pflb.eventmanager.exception.DataBaseException;
import ru.pflb.eventmanager.mapper.impl.UserMapper;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} succesfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername -  found by username: {}", username);
        return result;
    }

    @Override
    public UserDto create(UserDto user) {
        UserDto registeredUser = mapper.toDto(userRepository.save(mapper.toEntity(user)));
        log.info("IN register - user: {} succesfully registered", registeredUser.getUsername());
        return registeredUser;
    }

    @Override
    public UserDto update(UserDto dto) throws JsonProcessingException {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", dto.getId())));
        UserDto userDto = mapper.toDto(userRepository.save(mapper.toEntity(dto, user)));
        log.info("New User saved: {}", new ObjectMapper().writeValueAsString(userDto));
        return userDto;
    }

    @Override
    public UserDto get(Long id){
        return mapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", id))));
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
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
        userRepository.deleteById(id);
        boolean deleted = userRepository.findById(id).isEmpty();
        if (deleted) {
            log.info("User deleted by ID: {}", id);
        }
        return deleted;
    }
}
