package com.svvj.rajbika.rajbikaapi.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateUserAddressRequest {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private Integer pinCode;
}

