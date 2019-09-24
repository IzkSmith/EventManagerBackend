package ru.pflb.eventmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pflb.eventmanager.dto.UserDTO;
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
    @GetMapping
    public ResponseEntity<UserDTO> get(@RequestParam Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Validated(value = Validation.New.class) @RequestBody UserDTO dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(service.create(dto));
    }


}
