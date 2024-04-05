package com.blood.rescue.controller;

import com.blood.rescue.entity.BloodRequest;
import com.blood.rescue.service.BloodRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BloodRequestController {
    private final BloodRequestService bloodRequestService;

    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }
    @PostMapping("/donors")
    public ResponseEntity<?> postBloodRequest(@RequestBody BloodRequest  bloodRequest){
        return bloodRequestService.createNewRequest(bloodRequest);
    }
}
