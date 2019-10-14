package ru.pflb.eventmanager.rest.controller.ExceptionHandler;

import ru.pflb.eventmanager.exception.NonExistingCityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class CityExceptionHandler {
    @RestControllerAdvice
    public class SuperHeroExceptionHandler {

        @ExceptionHandler(NonExistingCityException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public void handleNonExistingHero() {
        }
    }
}
