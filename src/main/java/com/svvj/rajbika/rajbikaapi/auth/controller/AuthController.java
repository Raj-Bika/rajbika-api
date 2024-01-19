package com.svvj.rajbika.rajbikaapi.auth.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.svvj.rajbika.rajbikaapi.auth.dto.SignInRequest;
import com.svvj.rajbika.rajbikaapi.auth.dto.SignupRequest;
import com.svvj.rajbika.rajbikaapi.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        try {
            // Create the user in Firebase Authentication
            UserRecord userRecord = authService.signUpInFirebase(signupRequest);

            log.info("user that is signing up {}", userRecord);

            ResponseEntity<String> responseEntity = authService.saveUserInDB(signupRequest);

            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                return ResponseEntity.ok("User signed up successfully");
            } else {
                // If there was an issue creating the user in the user-service, delete the user from Firebase Authentication
                authService.deleteFirebaseUser(userRecord.getUid());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
            }
        } catch (FirebaseAuthException e) {
            // Handle Firebase Authentication exception
            log.error("Firebase Create User Exception Occurred: " +  e.getAuthErrorCode() + " " + e.getErrorCode());
            log.error("status code: " + e.getHttpResponse().getStatusCode());
            return ResponseEntity.status( e.getHttpResponse().getStatusCode()).body("Exception Occurred: " + e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {



        return new ResponseEntity<>(HttpStatus.OK);
    }



}
