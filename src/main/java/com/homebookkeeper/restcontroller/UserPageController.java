package com.homebookkeeper.restcontroller;

import com.homebookkeeper.DTO.BalanceDTO;
import com.homebookkeeper.DTO.EventDTO;
import com.homebookkeeper.model.Balance;
import com.homebookkeeper.model.Event;
import com.homebookkeeper.model.EventType;
import com.homebookkeeper.service.BalanceService;
import com.homebookkeeper.service.CategoryService;
import com.homebookkeeper.service.EventService;
import com.homebookkeeper.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UserPageController {
    private BalanceService balanceService;
    private ModelMapper modelMapper;
    private CategoryService categoryService;
    private UserService userService;
    private EventService eventService;

    public UserPageController(BalanceService balanceService, ModelMapper modelMapper, CategoryService categoryService, UserService userService, EventService eventService) {
        this.balanceService = balanceService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
        this.eventService = eventService;
    }


    @GetMapping(path = "/user/balance")
    public ResponseEntity<BalanceDTO> getBalance(@RequestParam Long id) {
        return new ResponseEntity<BalanceDTO>(convertToBalanceDTO(balanceService.getById(id).orElse(null)), HttpStatus.OK);
    }



    @PostMapping(path = "/events")
    public ResponseEntity<EventDTO> createEvent(@RequestBody Event event) {
        if(event == null) {
            return  new ResponseEntity<EventDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<EventDTO>(convertToEventDTO(eventService.save(event)), HttpStatus.OK);
    }

    @GetMapping(path = "/events")
    public ResponseEntity<List<EventDTO>> getEvents(@RequestParam Long userId){
        List<EventDTO> events = eventService.findAll(userId)
                .stream()
                .map(event -> { return convertToEventDTO(event);})
                .collect(Collectors.toList());

        return new ResponseEntity<List<EventDTO>>(events, HttpStatus.OK);
    }

    private BalanceDTO convertToBalanceDTO(Balance balance) {
        BalanceDTO balanceDTO = new BalanceDTO();
        modelMapper.map(balance, balanceDTO);
        return balanceDTO;
    }

    private EventDTO convertToEventDTO(Event event){
        EventDTO eventDTO = new EventDTO();
        modelMapper.map(event, eventDTO);
        return eventDTO;
    }
}
