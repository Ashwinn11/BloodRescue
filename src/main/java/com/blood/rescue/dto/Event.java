package com.blood.rescue.dto;

import com.blood.rescue.entity.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class Event {
    private List<BloodGroup> bloodGroupList;
    private String district;
    private BloodGroup bloodGroup;
    private String address;
    private String mobileNo;

    public Event(List<BloodGroup> bloodGroupList, String district,BloodGroup bloodGroup,String address,String mobileNo) {
        this.bloodGroupList=bloodGroupList;
        this.district=district;
        this.bloodGroup=bloodGroup;
        this.address=address;
        this.mobileNo=mobileNo;
    }
}
