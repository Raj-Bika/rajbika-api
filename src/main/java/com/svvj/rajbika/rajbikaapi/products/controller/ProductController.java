package com.svvj.rajbika.rajbikaapi.products.controller;

import com.svvj.rajbika.rajbikaapi.category.exception.CategoryNotFoundException;
import com.svvj.rajbika.rajbikaapi.products.dto.ProductRequest;
import com.svvj.rajbika.rajbikaapi.products.dto.ProductResponse;
import com.svvj.rajbika.rajbikaapi.products.exception.ProductNotFoundException;
import com.svvj.rajbika.rajbikaapi.products.model.Product;
import com.svvj.rajbika.rajbikaapi.products.service.ProductService;
import com.svvj.rajbika.rajbikaapi.shared.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductRequest product) throws CategoryNotFoundException {

            this.productService.createProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getProductDetails(@PathVariable("product-id") String productId) throws ProductNotFoundException {
        Product product = this.productService.getProductById(productId);
        return ResponseHandler.responseBuilder("Fetched Product successfully", HttpStatus.OK, HttpStatus.OK.value(), product);
    }

    @PutMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateProductDetails(@PathVariable("product-id") String productId, @RequestBody Product product) throws ProductNotFoundException {
        Product updatedProduct = this.productService.updateProductDetails(productId, product);
        return ResponseHandler.responseBuilder("Updated Product Successfully", HttpStatus.OK, HttpStatus.OK.value(), product);
    }
}
