package com.svvj.rajbika.rajbikaapi.category.controller;

import com.svvj.rajbika.rajbikaapi.category.dto.CreateCategoryRequest;
import com.svvj.rajbika.rajbikaapi.category.exception.CategoryNotFoundException;
import com.svvj.rajbika.rajbikaapi.category.exception.DuplicationCategoryNameException;
import com.svvj.rajbika.rajbikaapi.category.model.Category;
import com.svvj.rajbika.rajbikaapi.category.service.CategoryService;
import com.svvj.rajbika.rajbikaapi.shared.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllCategories() {
        List<Category> categoryList = this.categoryService.getAllCategories();
        return ResponseHandler.responseBuilder("Fetched Categories", HttpStatus.OK, HttpStatus.OK.value(), categoryList);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) throws DuplicationCategoryNameException {
        Category category = this.categoryService.createCategory(createCategoryRequest);
        return ResponseHandler.responseBuilder("Category created.", HttpStatus.CREATED, HttpStatus.CREATED.value(), category);
    }

    @PutMapping("/{category-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateCategory(@PathVariable("category-id") String categoryId, @RequestBody CreateCategoryRequest createCategoryRequest) throws CategoryNotFoundException {
        Category category = this.categoryService.updateCategory(categoryId, createCategoryRequest);
        return ResponseHandler.responseBuilder("Fetched category", HttpStatus.OK, HttpStatus.OK.value(), category);
    }



}
