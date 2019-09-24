package ru.pflb.eventmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.pflb.eventmanager.entity.City;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.transfer.Validation;

import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EventDTO extends AbstractDTO {

    @NotNull(groups = {Validation.New.class})
    private String name;

    @NotNull(groups = {Validation.New.class})
    private City city;

    @NotNull(groups = {Validation.New.class})
    private String date;

    @NotNull(groups = {Validation.New.class})
    private int maxMembers;

    @NotNull(groups = {Validation.New.class})
    private String description;

    @NotNull(groups = {Validation.New.class})
    private List<User> users;
}
