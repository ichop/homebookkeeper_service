package com.homebookkeeper.restcontroller;


import com.homebookkeeper.DTO.CategoryDTO;
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

    private CategoryService categoryService;
    private ModelMapper modelMapper;

    public CategoriesController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {
        return new ResponseEntity<CategoryDTO>(mapToCategoryDTO(categoryService.save(category)), HttpStatus.CREATED);
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesPerUser(@RequestParam Long userId) {
        List<CategoryDTO> categoryDTOS = categoryService.getAllByUserId(userId)
                .stream().map(category -> {
                    return mapToCategoryDTO(category);
                }).collect(Collectors.toList());
        return new ResponseEntity<List<CategoryDTO>>(categoryDTOS, HttpStatus.OK);
    }


    @PutMapping(path = "/category/id")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestParam Category category) {
        if (id != null && category != null) {
            return new ResponseEntity<CategoryDTO>(mapToCategoryDTO(categoryService.save(category)), HttpStatus.OK);
        }
        return new ResponseEntity<CategoryDTO>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/category")
    public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam Long id) {
        Category category = categoryService.getById(id).orElse(null);
        if (category == null) {
            return new ResponseEntity<CategoryDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CategoryDTO>(mapToCategoryDTO(category), HttpStatus.OK);
    }

    private CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        modelMapper.map(category, categoryDTO);
        return categoryDTO;
    }
}
