package ru.pflb.eventmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.pflb.eventmanager.transfer.Validation;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends AbstractDto {

    @NotNull(groups = {Validation.New.class})
    private String name;

}
