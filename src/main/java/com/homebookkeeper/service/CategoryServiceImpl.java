package com.homebookkeeper.service;


import com.homebookkeeper.model.Category;
import com.homebookkeeper.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public List<Category> getAllByUserId(Long userId) {
        System.out.println(userId);
        System.out.println(categoryRepo.findAllByUserId(userId));
        return categoryRepo.findAllByUserId(userId);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }
}
