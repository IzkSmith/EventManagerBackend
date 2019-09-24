package ru.pflb.eventmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.service.UserService;
import ru.pflb.eventmanager.transfer.Validation;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Validated(value = Validation.New.class) @RequestBody UserDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@Validated(value = Validation.Exists.class) @RequestBody UserDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }


}
