package com.blood.rescue.service;

import com.blood.rescue.dto.BloodRequestDTO;
import com.blood.rescue.entity.BloodGroup;
import com.blood.rescue.entity.BloodRequest;
import com.blood.rescue.entity.User;
import com.blood.rescue.event.EventPublisher;
import com.blood.rescue.kafka.producer.KafkaProducer;
import com.blood.rescue.repository.BloodRequestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodRequestService {
    private final BloodRequestRepository bloodRequestRepository;
    private final EventPublisher eventPublisher;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository, EventPublisher eventPublisher) {
        this.bloodRequestRepository = bloodRequestRepository;
        this.eventPublisher = eventPublisher;
    }

    public ResponseEntity<?> createNewRequest(BloodRequestDTO bloodRequestDTO) {
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setBloodGroup(BloodGroup.valueToEnum(bloodRequestDTO.getBloodGroup()));
        bloodRequest.setCity(bloodRequestDTO.getCity());
        bloodRequest.setDistrict(bloodRequestDTO.getDistrict());
        bloodRequest.setMobileNo(bloodRequestDTO.getMobileNo());
        bloodRequest.setAddress(bloodRequest.getAddress());
        bloodRequestRepository.save(bloodRequest);
        List<User> userList = eventPublisher.publishEvent(bloodRequestDTO);
        return new ResponseEntity<>(userList, HttpStatus.ACCEPTED);
    }

    public List<BloodRequest> getRequests(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo+1,pageSize);
        return bloodRequestRepository.findAll(pageable).getContent();
    }
}
