package ru.pflb.eventmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.service.EventService;
import ru.pflb.eventmanager.transfer.Validation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.get(id));
    }

    @GetMapping("/all{id}")
    public ResponseEntity<Page<EventDto>> getAll(@PathVariable int id, Pageable pageable) {
        Pageable page = PageRequest.of(id, 10);
        return ResponseEntity.ok(eventService.getAll(page));
    }

    @PostMapping
    public ResponseEntity<EventDto> create(@Validated(value = Validation.New.class) @RequestBody EventDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(eventService.create(dto));
    }

    @PutMapping
    public ResponseEntity<EventDto> update(@Validated(value = Validation.Exists.class) @RequestBody EventDto dto)
            throws JsonProcessingException {
        return ResponseEntity.ok(eventService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.delete(id));
    }
}
