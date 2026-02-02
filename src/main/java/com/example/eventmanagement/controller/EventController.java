package com.example.eventmanagement.controller;

import com.example.eventmanagement.dto.EventRequest;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new event")
    @PostMapping
    public ResponseEntity<Map<String, Long>> createEvent(
            @Valid @RequestBody EventRequest request) {

        Long eventId = service.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("eventId", eventId));
    }

    @Operation(summary = "Get event by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEventById(id));
    }

    @Operation(summary = "Update event by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request) {

        return ResponseEntity.ok(service.updateEvent(id, request));
    }

    @Operation(summary = "Delete event by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }
}
