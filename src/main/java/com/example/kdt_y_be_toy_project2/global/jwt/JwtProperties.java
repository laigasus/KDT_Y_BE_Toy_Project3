package com.example.kdt_y_be_toy_project2.global.jwt;

/**
 * JWT 기본 설정값
 */
public class JwtProperties {
    public static final int EXPIRATION_TIME = 600000; // 10분 -> 600000
    public static final String COOKIE_NAME = "JWT-AUTHENTICATION";
}