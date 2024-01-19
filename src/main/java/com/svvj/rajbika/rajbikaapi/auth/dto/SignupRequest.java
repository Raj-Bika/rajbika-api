package com.svvj.rajbika.rajbikaapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignupRequest {
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
