package com.homebookkeeper.service;

import com.homebookkeeper.model.Event;
import com.homebookkeeper.repository.EventRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepo eventRepo;

    public EventServiceImpl(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public Event save(Event event) {
        return eventRepo.save(event);
    }

    @Override
    public Event getById(Long id) {
        return eventRepo.findById(id).orElse(null);
    }

    @Override
    public List<Event> findAll(Long userId) {
        return eventRepo.findByUserId(userId);
    }

    @Override
    public List<Event> findByCategory(Long userId, Long categoryId) {
        return eventRepo.findByUserIdAndCategoryId(userId, categoryId);
    }
}
