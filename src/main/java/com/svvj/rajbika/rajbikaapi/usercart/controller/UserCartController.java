package com.svvj.rajbika.rajbikaapi.usercart.controller;

import com.svvj.rajbika.rajbikaapi.usercart.dto.AddProductToCartRequest;
import com.svvj.rajbika.rajbikaapi.usercart.service.UserCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/{user-id}/cart")
@Slf4j
@RequiredArgsConstructor
public class UserCartController {

    private final UserCartService cartService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createCartForUser(@PathVariable("user-id") String userId) {
        this.cartService.createUserCart(userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> checkingForCart(@PathVariable("user-id") String userId) {
        log.info("Getting {} as user ID", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addItem(@RequestBody AddProductToCartRequest addProductToCartRequest) {
        try{
            cartService.addItemToCart(addProductToCartRequest);
            return new ResponseEntity<>("", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}