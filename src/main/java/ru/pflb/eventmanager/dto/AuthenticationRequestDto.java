package ru.pflb.eventmanager.dto;

import lombok.Data;
import ru.pflb.eventmanager.transfer.Validation;

import javax.validation.constraints.NotNull;

@Data
public class AuthenticationRequestDto {

    @NotNull(groups = {Validation.New.class})
    private String username;

    @NotNull(groups = {Validation.New.class})
    private String password;
}
