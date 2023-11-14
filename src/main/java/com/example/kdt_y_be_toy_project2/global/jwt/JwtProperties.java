package com.example.kdt_y_be_toy_project2.global.jwt;

/**
 * JWT 기본 설정값
 */
public class JwtProperties {
    public static final int ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 10; // 10분 -> 600000
    public static final int REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 10;

    public static final String COOKIE_NAME = "JWT-AUTHENTICATION";
}