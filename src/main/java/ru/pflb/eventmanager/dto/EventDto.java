package ru.pflb.eventmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.pflb.eventmanager.transfer.Validation;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class EventDto extends AbstractDto {

    @NotNull(groups = {Validation.New.class})
    private String name;

    @NotNull(groups = {Validation.New.class})
    private Long cityId;

    @NotNull(groups = {Validation.New.class})
    private String date;

    @NotNull(groups = {Validation.New.class})
    private int maxMembers;

    @NotNull(groups = {Validation.New.class})
    private String description;
}
