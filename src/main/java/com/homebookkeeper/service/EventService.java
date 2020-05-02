package com.homebookkeeper.service;

import com.homebookkeeper.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event save(Event event);

    Event getById(Long id);

    List<Event> findAll(Long userId);

    List<Event> findByCategory(Long userId, Long categoryId);


}
