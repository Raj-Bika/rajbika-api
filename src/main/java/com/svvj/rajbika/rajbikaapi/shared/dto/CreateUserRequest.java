package com.svvj.rajbika.rajbikaapi.shared.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotNull(message = "First name of user is mandatory")
    private String firstName;

    @NotNull(message = "Last name of user is mandatory")
    private String lastName;

    @NotNull(message = "Email of user cannot be null or empty")
    @NotEmpty(message = "Email ID is mandatory")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Not a valid email")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Not a valid phone number")
    @NotEmpty(message = "Phone Number is required")
    private String phoneNumber;
}
