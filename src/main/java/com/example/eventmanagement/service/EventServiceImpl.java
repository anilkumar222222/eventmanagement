package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.EventRequest;
import com.example.eventmanagement.exception.EventNotFoundException;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long createEvent(EventRequest request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setLocation(request.getLocation());
        event.setDate(request.getDate());
        event.setDescription(request.getDescription());
        return repository.save(event).getId();
    }

    @Override
    public Event getEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id " + id));
    }

    @Override
    public Event updateEvent(Long id, EventRequest request) {
        Event event = getEventById(id);
        event.setName(request.getName());
        event.setLocation(request.getLocation());
        event.setDate(request.getDate());
        event.setDescription(request.getDescription());
        return repository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        repository.delete(event);
    }
}
