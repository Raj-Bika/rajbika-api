package com.svvj.rajbika.rajbikaapi.category.service;

import com.svvj.rajbika.rajbikaapi.category.dto.CreateCategoryRequest;
import com.svvj.rajbika.rajbikaapi.category.exception.CategoryNotFoundException;
import com.svvj.rajbika.rajbikaapi.category.exception.DuplicationCategoryNameException;
import com.svvj.rajbika.rajbikaapi.category.model.Category;
import com.svvj.rajbika.rajbikaapi.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<Category> categoryList = this.categoryRepository.findAll();
        return categoryList;
    }

    public Category createCategory(CreateCategoryRequest createCategoryRequest) throws DuplicationCategoryNameException {
        Category category = Category.builder().name(createCategoryRequest.getName()).build();
        if (this.categoryRepository.existsByName(category.getName())) {
            throw new DuplicationCategoryNameException("Category " + category.getName() + " already exists");
        }

        this.categoryRepository.save(category);
        return category;
    }

    public Category updateCategory(String categoryId, CreateCategoryRequest createCategoryRequest) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with id" + categoryId + " does not exist");
        }
        Category existingCategory = optionalCategory.get();
        existingCategory.setName(createCategoryRequest.getName());
        return existingCategory;
    }

    public Category getCategoryById(String categoryId) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with id" + categoryId + " does not exist");
        }
        return optionalCategory.get();
    }
}
