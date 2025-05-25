package com.example.post.service;

import com.example.post.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/user/{userId}")
    UserResponseDTO getUser(@PathVariable String userId);
}

