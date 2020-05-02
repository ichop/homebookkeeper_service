package com.homebookkeeper.restcontroller;

import com.homebookkeeper.DTO.EventDTO;
import com.homebookkeeper.mapper.EventMapper;
import com.homebookkeeper.model.Event;
import com.homebookkeeper.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping(path = "/events")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        if (eventDTO == null) {
            return new ResponseEntity<EventDTO>(HttpStatus.BAD_REQUEST);
        }
        Event event = eventService.save(eventMapper.toEntity(eventDTO));
        return new ResponseEntity<EventDTO>(eventMapper.toDTO(event), HttpStatus.OK);
    }

    @GetMapping(path = "/events")
    public ResponseEntity<List<EventDTO>> getEvents(@RequestParam Long userId) {
        List<EventDTO> events = eventService.findAll(userId)
                .stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<List<EventDTO>>(events, HttpStatus.OK);
    }


    @GetMapping(path = "/event")
    public ResponseEntity<Event> getEventById(@RequestParam Long eventId) {
        if (eventId != null) {
            Event event = eventService.getById(eventId);
            if (event != null) {
                return new ResponseEntity<Event>(event, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
    }


}
