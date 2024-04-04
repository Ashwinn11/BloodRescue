package com.blood.rescue.service;

import com.blood.rescue.repository.BloodRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class BloodRequestService {
    private final BloodRequestRepository bloodRequestRepository;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository) {
        this.bloodRequestRepository = bloodRequestRepository;
    }
}
