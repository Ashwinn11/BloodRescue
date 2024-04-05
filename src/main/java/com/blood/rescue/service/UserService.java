package com.blood.rescue.service;

import com.blood.rescue.dto.UserDTO;
import com.blood.rescue.entity.BloodGroup;
import com.blood.rescue.entity.User;
import com.blood.rescue.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> createUser(UserDTO userDTO) {
        Optional<User> optional = userRepository.findByEmailId(userDTO.getEmailId());
        if(optional.isEmpty()){
            User user = new User();
            user.setEmailId(userDTO.getEmailId());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setBloodGroup(BloodGroup.valueToEnum(userDTO.getBloodGroup()));
            user.setCity(userDTO.getCity());
            user.setDistrict(userDTO.getDistrict());
            user.setMobileNo(userDTO.getMobileNo());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Email already exists",HttpStatus.BAD_GATEWAY);
    }
}
