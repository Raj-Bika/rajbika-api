package com.svvj.rajbika.rajbikaapi.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}

