package com.example.user.dto;

import com.example.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String userId;
    private String userName;
    private String userImageUrl;

    public UserResponseDTO(UserEntity user) {
        this.userId = user.getUserid();
        this.userName = user.getUsername();
    }

}
