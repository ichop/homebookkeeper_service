package com.homebookkeeper.restcontroller;


import com.homebookkeeper.DTO.CategoryDTO;
import com.homebookkeeper.mapper.CategoryMapper;
import com.homebookkeeper.model.Category;
import com.homebookkeeper.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoriesController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoriesController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping(path = "/categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDto) {
        Category category = categoryService.save(categoryMapper.toEntity(categoryDto));
        return new ResponseEntity<CategoryDTO>(categoryMapper.toDTO(category), HttpStatus.CREATED);
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesPerUser(@RequestParam Long userId) {
        List<CategoryDTO> categoryDTOS = categoryService.getAllByUserId(userId)
                .stream().map(categoryMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<List<CategoryDTO>>(categoryDTOS, HttpStatus.OK);
    }


    @PutMapping(path = "/category")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO != null) {
            Category category = categoryService.save(categoryMapper.toEntity(categoryDTO));
            return new ResponseEntity<CategoryDTO>(categoryMapper.toDTO(category), HttpStatus.OK);
        }
        return new ResponseEntity<CategoryDTO>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/category")
    public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam Long id) {
        if (id != null) {
            Category category = categoryService.getById(id).orElse(null);
            if (category != null) {
                return new ResponseEntity<CategoryDTO>(categoryMapper.toDTO(category), HttpStatus.OK);
            }
        }
        return new ResponseEntity<CategoryDTO>(HttpStatus.BAD_REQUEST);
    }

}
