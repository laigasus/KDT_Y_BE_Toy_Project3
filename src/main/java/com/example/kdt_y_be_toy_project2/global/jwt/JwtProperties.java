package com.example.kdt_y_be_toy_project2.global.jwt;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class JwtProperties {
    public static final int ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 10;
    public static final int REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 10;

    public static final String COOKIE_NAME = "JWT-AUTHENTICATION";
}