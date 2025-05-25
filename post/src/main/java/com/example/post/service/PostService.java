package com.example.post.service;

import com.example.post.dto.CreatePostRequestDTO;
import com.example.post.dto.UserResponseDTO;
import com.example.post.model.PostEntity;
import com.example.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserClient userClient;

    public PostEntity createPost(CreatePostRequestDTO request) {
        String title = request.getTitle();
        String content = request.getContent();
        String writerId = request.getUserId();

        UserResponseDTO user = userClient.getUser(writerId);

        PostEntity posting = PostEntity.builder()
                .content(content)
                .title(title)
                .writer(user.getUserId())
                .build();

        return postRepository.save(posting);
    }


    public void deletePost(long id){
        Optional<PostEntity> post=postRepository.findById(id);
        if(post.isEmpty()){
            throw new RuntimeException("게시물 ㄴㄴ");
        }
        PostEntity postEntity=post.get();

        postRepository.delete(postEntity);
    }
}
