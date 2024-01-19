package com.svvj.rajbika.rajbikaapi.users.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id()
    private String id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private Integer pinCode;

}

