package com.svvj.rajbika.rajbikaapi.usercart.service;

import com.svvj.rajbika.rajbikaapi.usercart.dto.AddProductToCartRequest;
import com.svvj.rajbika.rajbikaapi.usercart.model.Cart;
import com.svvj.rajbika.rajbikaapi.usercart.model.CartItems;
import com.svvj.rajbika.rajbikaapi.usercart.repository.UserCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCartService {
    private final UserCartRepository cartRepository;
//    private final RestTemplate restTemplate;
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8086/api/product";

    public void addItemToCart(AddProductToCartRequest addProductToCartRequest) {
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(PRODUCT_SERVICE_URL, String.class);
//
//        log.info("Getting Product: " + responseEntity);

    }

    public void createUserCart(String userId) {

        List<CartItems> cartItemsList= new ArrayList<CartItems>();
        Cart cart = Cart.builder()
                .userId(userId).cartItemsList(cartItemsList).build();
        this.cartRepository.save(cart);
        log.info("Cart for user {} is created", userId);
    }
}