package com.svvj.rajbika.rajbikaapi.users.controller;

import com.svvj.rajbika.rajbikaapi.users.dto.CreateUserAddressRequest;
import com.svvj.rajbika.rajbikaapi.users.dto.UserAddressListResponse;
import com.svvj.rajbika.rajbikaapi.users.model.User;
import com.svvj.rajbika.rajbikaapi.users.dto.UserResponse;
import com.svvj.rajbika.rajbikaapi.users.service.UserService;
import com.svvj.rajbika.rajbikaapi.shared.dto.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    public final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid CreateUserRequest userRequest) {
        log.info("Getting create request");
        try {
            this.userService.createUser(userRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getUserById(@PathVariable("user-id") String userId) {
        Optional<User> optionalUser = userService.getUserById(userId);
        return optionalUser.map(user ->
                                        new ResponseEntity<>(
                                                UserResponse.builder()
                                                        .firstName(user.getFirstName())
                                                        .lastName(user.getLastName())
                                                        .id(user.getId())
                                                        .email(user.getEmail())
                                                        .build(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{user-id}/address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUserAddress(@PathVariable("user-id") String userId, @RequestBody CreateUserAddressRequest createUserAddressRequest) {
        Boolean addressSaved = this.userService.createAddressForUser(userId, createUserAddressRequest);

        if(addressSaved) {
            return new ResponseEntity<>("Address is created", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{user-id}/address")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserAddressListResponse>> getUserAddressList(@PathVariable("user-id") String userId ) {
        List<UserAddressListResponse> addresses = userService.getUserAddresses(userId);

        if (!addresses.isEmpty()) {
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
