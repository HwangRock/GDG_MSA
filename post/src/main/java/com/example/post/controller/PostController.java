package com.example.post.controller;

import com.example.post.dto.CreatePostRequestDTO;
import com.example.post.model.PostEntity;
import com.example.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequestDTO request){

        PostEntity posted=postService.createPost(request);

        return ResponseEntity.ok(
                posted
        );
    }

    @DeleteMapping("/deletePost/{postid}")
    public ResponseEntity<?> deletePost(@PathVariable long postid){
        postService.deletePost(postid);

        return ResponseEntity.ok(
                "삭제함."
        );
    }
}
