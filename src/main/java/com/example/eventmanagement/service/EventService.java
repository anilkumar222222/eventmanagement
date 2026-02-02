package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.EventRequest;
import com.example.eventmanagement.model.Event;

public interface EventService {

    Long createEvent(EventRequest request);

    Event getEventById(Long id);

    Event updateEvent(Long id, EventRequest request);

    void deleteEvent(Long id);
}
