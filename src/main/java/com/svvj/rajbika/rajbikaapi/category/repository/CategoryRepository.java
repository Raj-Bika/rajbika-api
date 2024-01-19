package com.svvj.rajbika.rajbikaapi.category.repository;

import com.svvj.rajbika.rajbikaapi.category.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    boolean existsByName(String name);
}
