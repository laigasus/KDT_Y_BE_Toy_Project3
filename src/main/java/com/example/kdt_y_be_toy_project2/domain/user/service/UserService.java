package com.example.kdt_y_be_toy_project2.domain.user.service;

import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserResponse;
import com.example.kdt_y_be_toy_project2.domain.user.dto.LoginRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.LoginResponse;

public interface UserService {

    CreateUserResponse signup(CreateUserRequest createUserRequest);

    LoginResponse login(LoginRequest loginRequest);
}
