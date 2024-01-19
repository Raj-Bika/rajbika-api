package com.svvj.rajbika.rajbikaapi.products.service;

import com.svvj.rajbika.rajbikaapi.category.exception.CategoryNotFoundException;
import com.svvj.rajbika.rajbikaapi.category.exception.DuplicationCategoryNameException;
import com.svvj.rajbika.rajbikaapi.category.model.Category;
import com.svvj.rajbika.rajbikaapi.category.service.CategoryService;
import com.svvj.rajbika.rajbikaapi.inventory.model.AddInventory;
import com.svvj.rajbika.rajbikaapi.products.dto.ProductRequest;
import com.svvj.rajbika.rajbikaapi.products.dto.ProductResponse;
import com.svvj.rajbika.rajbikaapi.products.exception.ProductNotFoundException;
import com.svvj.rajbika.rajbikaapi.products.model.Product;
import com.svvj.rajbika.rajbikaapi.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    //    private final RestTemplate restTemplate;
    private static final String INVENTORY_SERVICE_URL = "http://localhost:8087/api/inventory";
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public void createProduct(ProductRequest productRequest) throws CategoryNotFoundException {
        Category category = this.categoryService.getCategoryById(productRequest.getCategoryId());

        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .productDescription(productRequest.getProductDescription())
                .skuCode(productRequest.getSkuCode())
                .category(category)
                .build();


        AddInventory addInventory = AddInventory.builder().skuCode(product.getSkuCode()).quantity(0).build();
        List<AddInventory> addInventoryList = new ArrayList<>();
        addInventoryList.add(addInventory);
        productRepository.save(product);
        log.info("Product {} saved successfully", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .build();
    }

    public Product getProductById(String productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            log.error("No Product found in service");
            throw new ProductNotFoundException("No Product found with ID: " + productId);
        }
        return Product.builder().productName(optionalProduct.get().getProductName())
                .productDescription(optionalProduct.get().getProductDescription())
                .productPrice(optionalProduct.get().getProductPrice())
                .id(optionalProduct.get().getId())
                .skuCode(optionalProduct.get().getSkuCode()).build();

    }

    public Product updateProductDetails(String productId, Product updatedProduct) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            log.error("No Product found in service");
            throw new ProductNotFoundException("No Product found with ID: " + productId);
        }
        Product existingProduct = optionalProduct.get();
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductDescription(updatedProduct.getProductDescription());
        existingProduct.setProductPrice(updatedProduct.getProductPrice());
        productRepository.save(existingProduct);
        return existingProduct;
    }
}

