package ru.pflb.eventmanager.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.security.jwt.JwtTokenProvider;
import ru.pflb.eventmanager.service.UserService;
import ru.pflb.eventmanager.transfer.Validation;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public UserController( JwtTokenProvider jwtTokenProvider, UserService service) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Validated(value = Validation.New.class) @RequestBody UserDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(userService.create(dto));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@Validated(value = Validation.Exists.class) @RequestBody UserDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(userService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }


}
