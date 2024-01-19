package com.svvj.rajbika.rajbikaapi.products.repository;

import com.svvj.rajbika.rajbikaapi.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {


}
