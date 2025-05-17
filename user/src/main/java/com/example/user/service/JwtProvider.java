package com.example.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {

    // 나중에 spring cloud config에 넣어야함
    private String SECRET_KEY="j3H9$gR2!vBz7KqLrDp@Wz1fXy+TmC4n";

    private static final long ACCESS_TOKEN_EXPIRATION = 1000*60*30; // 30분
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String createAccessToken(String username){
        return Jwts.builder()
                .setClaims(Map.of(
                        "userName", username
                ))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
