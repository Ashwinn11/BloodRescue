package com.blood.rescue.controller;

import com.blood.rescue.dto.BloodRequestDTO;
import com.blood.rescue.entity.BloodRequest;
import com.blood.rescue.service.BloodRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BloodRequestController {
    private final BloodRequestService bloodRequestService;

    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }

    @PostMapping("/donors")
    public ResponseEntity<?> postBloodRequest(@RequestBody BloodRequestDTO bloodRequestDTO){
        return bloodRequestService.createNewRequest(bloodRequestDTO);
    }

    
    @GetMapping("/donors")
    public List<BloodRequest> getRequests(@RequestParam(defaultValue = "1")int pageNo,
                                          @RequestParam(defaultValue = "10") int pageSize){
        return bloodRequestService.getRequests(pageNo,pageSize);
    }
}
