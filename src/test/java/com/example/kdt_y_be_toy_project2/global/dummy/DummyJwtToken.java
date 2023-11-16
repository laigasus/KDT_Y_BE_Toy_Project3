package com.example.kdt_y_be_toy_project2.global.dummy;

import com.example.kdt_y_be_toy_project2.global.jwt.JwtKey;

public interface DummyJwtToken {
    String SECRET = "Secret"; // HS256 (대칭키)
    int EXPIRATION_TIME = 1000 * 60; // 1분;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER = "Authorization";
}
