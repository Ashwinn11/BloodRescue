package com.blood.rescue.controller;

import com.blood.rescue.service.BloodRequestService;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BloodRequestController {
    private final BloodRequestService bloodRequestService;

    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }
}
