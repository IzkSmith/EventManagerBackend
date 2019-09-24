package ru.pflb.eventmanager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository ;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User get(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Пользователь с ID %d не существует.", id)));
    }

}
