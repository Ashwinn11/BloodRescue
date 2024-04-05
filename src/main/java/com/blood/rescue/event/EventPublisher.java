package com.blood.rescue.event;

import com.blood.rescue.dto.BloodRequestDTO;
import com.blood.rescue.entity.BloodGroup;
import com.blood.rescue.entity.User;
import com.blood.rescue.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventPublisher {
    private final UserRepository userRepository;

    public EventPublisher(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> publishEvent(BloodRequestDTO bloodRequestDTO) {
        BloodGroup bloodGroup = BloodGroup.valueToEnum(bloodRequestDTO.getBloodGroup());
        String district = bloodRequestDTO.getDistrict();
        List<User> userList = userRepository.findPotentialMatch(district,bloodGroup);
        return userList;
    }
}
