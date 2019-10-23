package ru.pflb.eventmanager.controller.ExceptionHandler;

import ru.pflb.eventmanager.exception.NonExistingCityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CityExceptionHandler {

    @ExceptionHandler(NonExistingCityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNonExistingCity() {
    }
}
