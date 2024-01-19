package com.svvj.rajbika.rajbikaapi.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddressListResponse {
    private String id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private Integer pinCode;
}

