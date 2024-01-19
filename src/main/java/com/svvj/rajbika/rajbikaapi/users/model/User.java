package com.svvj.rajbika.rajbikaapi.users.model;

import com.svvj.rajbika.rajbikaapi.users.model.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id()
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Address> addressList;

    public List<Address> getAddresses() {
        return this.addressList;
    }
}

