package com.homebookkeeper.service;

import com.homebookkeeper.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CategoryService {
    Optional<Category> getById(Long id);

    List<Category> getAllByUserId(Long userId);

    Category save(Category category);
}
