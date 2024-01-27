package com.svvj.rajbika.rajbikaapi.users.service;


import com.svvj.rajbika.rajbikaapi.shared.dto.CreateUserRequest;
import com.svvj.rajbika.rajbikaapi.usercart.service.UserCartService;
import com.svvj.rajbika.rajbikaapi.users.dto.CreateUserAddressRequest;
import com.svvj.rajbika.rajbikaapi.users.dto.UserAddressListResponse;
import com.svvj.rajbika.rajbikaapi.users.dto.UserResponse;
import com.svvj.rajbika.rajbikaapi.users.model.Address;
import com.svvj.rajbika.rajbikaapi.users.model.User;
import com.svvj.rajbika.rajbikaapi.users.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserCartService userCartService;


    public User createUser(@Valid CreateUserRequest userRequest) {
        User user = User.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .build();
        this.userRepository.save(user);
        this.userCartService.createUserCart(user.getId());
        log.info("User {} saved successfully", user.getId());
        return user;
    }

    public List<UserResponse> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public Optional<User> getUserById(String userId) {
        return this.userRepository.findById(userId);
    }

    public Boolean createAddressForUser(String userId, CreateUserAddressRequest createUserAddressRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Address address = Address.builder()
                    .id(UUID.randomUUID().toString())
                    .addressLine1(createUserAddressRequest.getAddressLine1())
                    .addressLine2(createUserAddressRequest.getAddressLine2())
                    .city(createUserAddressRequest.getCity())
                    .state(createUserAddressRequest.getState())
                    .pinCode(createUserAddressRequest.getPinCode())
                    .build();
            if (user.getAddressList() != null && user.getAddressList().stream().toArray().length > 0) {
                user.getAddressList().add(address);
            } else {
                List<Address> newAddressList = new ArrayList<>();
                newAddressList.add(address);
                user.setAddressList(newAddressList);
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<UserAddressListResponse> getUserAddresses(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser.map(User::getAddresses).orElse(List.of()).stream().map(this::mapToUserAddressList).toList();
    }

    private UserAddressListResponse mapToUserAddressList(Address addresses) {
        UserAddressListResponse userAddressListResponse = UserAddressListResponse.builder()
                .id(addresses.getId())
                .addressLine1(addresses.getAddressLine1())
                .addressLine2(addresses.getAddressLine2())
                .city(addresses.getCity())
                .state(addresses.getState())
                .pinCode(addresses.getPinCode())
                .build();

        return userAddressListResponse;
    }

    public String createUserWithPhoneNumber(String phoneNumber) {
        return "";
    }

    public boolean emailAlreadyExists(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByEmail(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("Email Not Found");
        return user.get();
    }
}
