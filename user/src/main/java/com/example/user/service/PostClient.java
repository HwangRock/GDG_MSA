package com.example.user.service;

import com.example.user.dto.PostResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Component
@FeignClient(name = "post-service")
public interface PostClient {

    @GetMapping("/api/post/user")
    List<PostResponseDTO> getPostsByUserId(@RequestParam String userId);
}
