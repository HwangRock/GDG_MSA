package com.example.user.controller;

import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.RegisterRequestDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.model.UserEntity;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userSignup")
    public ResponseEntity<?> userSignup(@RequestBody RegisterRequestDTO request){
        UserEntity result=userService.userRegister(request);

        return ResponseEntity.ok(
                result
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequestDTO request){
        String accessToken=userService.tokenProvider(request);

        return ResponseEntity.ok(
                accessToken
        );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String userId) {
        UserEntity user = userService.findByUserId(userId);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

}
