package ru.pflb.eventmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.pflb.eventmanager.transfer.Validation;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CityDto extends AbstractDto {

    @NotNull(groups = {Validation.New.class})
    private String name;

    @NotNull(groups = {Validation.New.class})
    private List<EventDto> events = new ArrayList<>();
}
