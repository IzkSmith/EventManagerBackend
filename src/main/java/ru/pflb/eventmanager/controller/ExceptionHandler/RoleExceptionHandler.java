package ru.pflb.eventmanager.controller.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pflb.eventmanager.exception.NonExistingRoleException;

@RestControllerAdvice
public class RoleExceptionHandler {

    @ExceptionHandler(NonExistingRoleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNonExistingRole() {
    }
}