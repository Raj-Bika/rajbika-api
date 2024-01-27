package com.svvj.rajbika.rajbikaapi.auth.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.svvj.rajbika.rajbikaapi.auth.dto.SignupRequest;
import com.svvj.rajbika.rajbikaapi.auth.model.Role;
import com.svvj.rajbika.rajbikaapi.security.service.JwtService;
import com.svvj.rajbika.rajbikaapi.shared.dto.CreateUserRequest;
import com.svvj.rajbika.rajbikaapi.users.model.User;
import com.svvj.rajbika.rajbikaapi.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User createUser(SignupRequest signupRequest) throws Exception {
        if (this.emailAlreadyExists(signupRequest.getEmail())) {
            throw new Exception("Email Already in use");
        }
        return saveUserInDB(signupRequest);
    }

    public boolean emailAlreadyExists(String email) {
        return this.userService.emailAlreadyExists(email);
    }


    public User saveUserInDB(SignupRequest signupRequest) {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .email(signupRequest.getEmail())
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .phoneNumber(signupRequest.getPhoneNumber())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();
        User user = userService.createUser(createUserRequest);
        return user;
    }

    public String getToken(User user) {
        return this.jwtService.generateToken(user);
    }

    public User getUserByEmail(String email){
        return userService.loadUserByUsername(email);
    }
}
