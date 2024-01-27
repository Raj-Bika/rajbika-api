package com.svvj.rajbika.rajbikaapi.auth.controller;

import com.svvj.rajbika.rajbikaapi.auth.dto.AuthenticationResponse;
import com.svvj.rajbika.rajbikaapi.auth.dto.SignInRequest;
import com.svvj.rajbika.rajbikaapi.auth.dto.SignupRequest;
import com.svvj.rajbika.rajbikaapi.auth.service.AuthService;
import com.svvj.rajbika.rajbikaapi.shared.response.ResponseHandler;
import com.svvj.rajbika.rajbikaapi.users.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest) throws Exception {
        User user = authService.createUser(signupRequest);
        if (user.equals(null)) {
            log.error("there was a problem creating the user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
        AuthenticationResponse authenticationResponse = AuthenticationResponse
                .builder()
                .token(authService.getToken(user))
                .build();
        return ResponseHandler.responseBuilder(
                "Account created",
                HttpStatus.CREATED,
                HttpStatus.OK.value(),
                authenticationResponse
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()
                )
        );
        User user = authService.getUserByEmail(signInRequest.getEmail());
        AuthenticationResponse authenticationResponse = AuthenticationResponse
                .builder()
                .token(authService.getToken(user))
                .build();
        return ResponseHandler.responseBuilder(
                "User Authenticated",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                authenticationResponse
        );
    }


}
