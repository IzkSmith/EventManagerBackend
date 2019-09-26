package ru.pflb.eventmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pflb.eventmanager.dto.CityDto;
import ru.pflb.eventmanager.service.CityService;
import ru.pflb.eventmanager.transfer.Validation;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/cities")
     public ResponseEntity<Page<CityDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<CityDto> create(@Validated(value = Validation.New.class) @RequestBody CityDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<CityDto> update(@Validated(value = Validation.Exists.class) @RequestBody CityDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}

