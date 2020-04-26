package com.homebookkeeper.service;

import com.homebookkeeper.model.Event;
import com.homebookkeeper.repository.EventRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public List<Event> findAll(Long userId) {
        return  eventRepo.findByUserId(userId);
    }

    @Override
    public List<Event> findByCategory(Long userId, Long categoryId) {
       return eventRepo.findByUserIdAndCategoryId(userId, categoryId);
    }
}
