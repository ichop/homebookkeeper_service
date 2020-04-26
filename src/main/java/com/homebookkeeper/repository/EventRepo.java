package com.homebookkeeper.repository;


import com.homebookkeeper.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByUserIdAndCategoryId(Long userId, Long categoryId);

    List<Event> findByUserId(Long userId);
}
