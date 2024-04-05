package com.blood.rescue.controller;

import com.blood.rescue.dto.UserDTO;
import com.blood.rescue.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
}
