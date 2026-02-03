The Event Management REST API is a Spring Boot–based web application designed to manage events efficiently. It allows event organizers to create, read, update, and delete events using RESTful web services. The project follows industry best practices and demonstrates real-world backend development using Spring Boot.


API Endpoints

Create Event

User Story:
As an Event Organizer, I want to create new events so that I can manage events efficiently.

Endpoint: POST /api/events

Request Body (JSON):

{
  "name": "Tech Conference",
  "location": "Bangalore",
  "date": "2026-03-15",
  "description": "Spring Boot Tech Event"
}


Success Response:

{
  "id": 1,
  "message": "Event created successfully"
}


Error Handling:

Invalid input data returns 400 Bad Request

Read Event

User Story:
As an Event Attendee, I want to retrieve event details so that I can view event information.

Endpoint: GET /api/events/{id}

Success Response (JSON):

{
  "id": 1,
  "name": "Tech Conference",
  "location": "Bangalore",
  "date": "2026-03-15",
  "description": "Spring Boot Tech Event"
}


Error Handling:

Non-existent event ID returns 404 Not Found

Update Event

Endpoint: PUT /api/events/{id}

Description: Updates existing event details

Delete Event

Endpoint: DELETE /api/events/{id}

Description: Deletes an event by ID
