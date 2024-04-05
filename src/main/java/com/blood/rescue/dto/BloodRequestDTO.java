package com.blood.rescue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BloodRequestDTO {
    private String bloodGroup;
    private String address;
    private String city;
    private String district;
    private String mobileNo;

}
