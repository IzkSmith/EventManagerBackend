package ru.pflb.eventmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.entity.Role;
import ru.pflb.eventmanager.transfer.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends AbstractDto {
    @NotNull(groups = {Validation.New.class, Validation.Login.class})
    private String username;

    @NotNull(groups = {Validation.New.class, Validation.Login.class})
    private String password;

    @NotNull(groups = {Validation.New.class})
    private String email;

    @NotNull(groups = {Validation.New.class})
    private List<Long> eventsId = new ArrayList<>();

    @NotNull(groups = {Validation.New.class})
    private List<Long> roles = new ArrayList<>();

}