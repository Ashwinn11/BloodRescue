package com.blood.rescue.service;

import com.blood.rescue.dto.BloodRequestDTO;
import com.blood.rescue.dto.Event;
import com.blood.rescue.entity.BloodGroup;
import com.blood.rescue.entity.BloodRequest;
import com.blood.rescue.kafka.producer.KafkaProducer;
import com.blood.rescue.repository.BloodRequestRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BloodRequestService {
    private final BloodRequestRepository bloodRequestRepository;
    private final KafkaProducer kafkaProducer;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository, KafkaProducer kafkaProducer) {
        this.bloodRequestRepository = bloodRequestRepository;
        this.kafkaProducer = kafkaProducer;
    }


    public ResponseEntity<?> createNewRequest(BloodRequestDTO bloodRequestDTO) {
        // creating blood-request and saving it in repository
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setBloodGroup(BloodGroup.valueToEnum(bloodRequestDTO.getBloodGroup()));
        bloodRequest.setCity(bloodRequestDTO.getCity());
        bloodRequest.setDistrict(bloodRequestDTO.getDistrict());
        bloodRequest.setMobileNo(bloodRequestDTO.getMobileNo());
        bloodRequest.setAddress(bloodRequestDTO.getAddress());
        bloodRequestRepository.save(bloodRequest);

        //finding compatible blood-groups
        List<BloodGroup> bloodGroupList = compatibleBloodGroups(bloodRequest.getBloodGroup());



        //building the address from the request-body
        String address= buildAddress(bloodRequest.getAddress(),bloodRequest.getCity(),bloodRequest.getDistrict());


        //creating and publishing the event to find suitable donors and notifying them using message queue
        Event event = new Event(bloodGroupList,bloodRequestDTO.getDistrict()
                ,bloodRequest.getBloodGroup()
                ,address,
                bloodRequest.getMobileNo());
        kafkaProducer.sendMessage("blood-available",event);


        return new ResponseEntity<>("Event published", HttpStatus.ACCEPTED);
    }

    public List<BloodRequest> getRequests(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo+1,pageSize);
        return bloodRequestRepository.findAll(pageable).getContent();
    }

    public List<BloodGroup> compatibleBloodGroups(BloodGroup bloodGroup){
        List<BloodGroup> compatibleBloodGroups = new ArrayList<>();

        switch (bloodGroup) {
            case O_NEGATIVE:
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                break;
            case O_POSITIVE:
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.O_POSITIVE);
                break;
            case A_NEGATIVE:
                compatibleBloodGroups.add(BloodGroup.A_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                break;
            case A_POSITIVE:
                compatibleBloodGroups.add(BloodGroup.A_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.A_POSITIVE);
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.O_POSITIVE);
                break;
            case B_NEGATIVE:
                compatibleBloodGroups.add(BloodGroup.B_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                break;
            case B_POSITIVE:
                compatibleBloodGroups.add(BloodGroup.B_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.B_POSITIVE);
                compatibleBloodGroups.add(BloodGroup.O_POSITIVE);
                break;
            case AB_NEGATIVE:
                compatibleBloodGroups.add(BloodGroup.AB_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.A_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.B_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                break;
            case AB_POSITIVE:
                compatibleBloodGroups.add(BloodGroup.AB_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.A_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.B_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.O_NEGATIVE);
                compatibleBloodGroups.add(BloodGroup.AB_POSITIVE);
                compatibleBloodGroups.add(BloodGroup.A_POSITIVE);
                compatibleBloodGroups.add(BloodGroup.B_POSITIVE);
                compatibleBloodGroups.add(BloodGroup.O_POSITIVE);
                break;
            default:
                break;
        }
        return compatibleBloodGroups;
    }
    private String buildAddress(String address,String city,String district){
        return address+","+ city +","+ district;
    }
}
