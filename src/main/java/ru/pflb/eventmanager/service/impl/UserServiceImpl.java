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
import ru.pflb.eventmanager.entity.Status;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.exception.DataBaseException;
import ru.pflb.eventmanager.mapper.impl.UserMapper;
import ru.pflb.eventmanager.repository.RoleRepository;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User register(User user){
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} succesfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public UserDto create(UserDto dto) throws JsonProcessingException {
        UserDto userDto = mapper.toDto(userRepository.save(mapper.toEntity(dto)));
        log.info("New User saved: {}", new ObjectMapper().writeValueAsString(userDto));
        return userDto;
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
    public UserDto getByCredentials(String username, String password) {
        return mapper.toDto(userRepository.getByUsernameAndPassword(username, password)
                .orElseThrow(() -> new DataBaseException("Неправильная пара логин-пароль.")));
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
        boolean deleted = !userRepository.findById(id).isPresent();
        if (deleted) {
            log.info("User deleted by ID: {}", id);
        }
        return deleted;
    }
}
