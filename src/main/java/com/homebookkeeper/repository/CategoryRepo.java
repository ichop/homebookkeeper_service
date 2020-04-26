package com.homebookkeeper.repository;

import com.homebookkeeper.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAllByUserId(Long userId);
}
