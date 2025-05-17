package com.example.post.repository;

import com.example.post.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    Optional<PostEntity> findById(long id);
}
