package com.blood.rescue.service;

import com.blood.rescue.entity.BloodRequest;
import com.blood.rescue.repository.BloodRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BloodRequestService {
    private final BloodRequestRepository bloodRequestRepository;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository) {
        this.bloodRequestRepository = bloodRequestRepository;
    }

    public ResponseEntity<?> createNewRequest(BloodRequest bloodRequest) {
        bloodRequestRepository.save(bloodRequest);
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }
}
