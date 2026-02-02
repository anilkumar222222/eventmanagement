package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------------- CREATE EVENT ----------------
    @Test
    void createEvent_success() throws Exception {

        Mockito.when(eventService.createEvent(Mockito.any()))
                .thenReturn(1L);

        String requestBody = """
                {
                  "name": "Tech Conference",
                  "location": "Hyderabad",
                  "date": "2026-06-01",
                  "description": "Spring Boot Event"
                }
                """;

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventId").value(1L));
    }

    // ---------------- GET EVENT ----------------
    @Test
    void getEvent_success() throws Exception {

        Event event = new Event();
        event.setId(1L);
        event.setName("Tech Conference");
        event.setLocation("Hyderabad");
        event.setDate(LocalDate.of(2026, 6, 1));
        event.setDescription("Spring Boot Event");

        Mockito.when(eventService.getEventById(1L))
                .thenReturn(event);

        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tech Conference"))
                .andExpect(jsonPath("$.location").value("Hyderabad"));
    }

    // ---------------- UPDATE EVENT ----------------
    @Test
    void updateEvent_success() throws Exception {

        Event updatedEvent = new Event();
        updatedEvent.setId(1L);
        updatedEvent.setName("Updated Event");
        updatedEvent.setLocation("Bangalore");
        updatedEvent.setDate(LocalDate.of(2026, 7, 1));

        Mockito.when(eventService.updateEvent(Mockito.eq(1L), Mockito.any()))
                .thenReturn(updatedEvent);

        String requestBody = """
                {
                  "name": "Updated Event",
                  "location": "Bangalore",
                  "date": "2026-07-01"
                }
                """;

        mockMvc.perform(put("/api/events/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Event"));
    }

    // ---------------- DELETE EVENT ----------------
    @Test
    void deleteEvent_success() throws Exception {

        Mockito.doNothing().when(eventService).deleteEvent(1L);

        mockMvc.perform(delete("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Event deleted successfully"));
    }
}
