package com.example.user.controller;

import com.example.user.dto.RegisterRequestDTO;
import com.example.user.model.UserEntity;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
