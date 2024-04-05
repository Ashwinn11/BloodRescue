package com.blood.rescue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    private String bloodGroup;
    private String city;
    private String district;
    private String emailId;
    private String firstName;
    private String lastName;
    private String mobileNo;
}
