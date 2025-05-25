package com.example.user.service;

import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.RegisterRequestDTO;
import com.example.user.model.UserEntity;
import com.example.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtProvider jwtProvider;

    public UserEntity userRegister(RegisterRequestDTO request){
        String id=request.getUserid();
        String name=request.getUsername();
        String pw=request.getPassword();

        if(userRepository.existsByUserid(id)){
            throw new RuntimeException("이미 존재하는 id입니다.");
        }
        if(userRepository.existsByUsername(name)){
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }


        String encryptedPassword=passwordEncoder.encode(pw);

        UserEntity entity=UserEntity.builder()
                .userid(id)
                .username(name)
                .password(encryptedPassword)
                .build();

        return userRepository.save(entity);
    }

    public String tokenProvider(LoginRequestDTO request){
        String id=request.getUserid();
        String pw=request.getPassword();

        Optional<UserEntity> wantUser=userRepository.findByUserid(id);
        if(wantUser.isEmpty()){
            throw new RuntimeException("ID나 비밀번호를 다시 확인해주세요.");
        }

        if(!passwordEncoder.matches(pw,wantUser.get().getPassword())){
            throw new RuntimeException("ID나 비밀번호를 다시 확인해주세요.");
        }


        String userName=wantUser.get().getUsername();

        String accessToken= jwtProvider.createAccessToken(userName);

        return accessToken;
    }

    public UserEntity findByUserId(String userId){
        return userRepository.findByUserid(userId).get();
    }

}
