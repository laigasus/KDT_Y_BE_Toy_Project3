package com.example.kdt_y_be_toy_project2.domain.user.service;

import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserResponse;

public interface UserService{

    CreateUserResponse signup(CreateUserRequest createUserRequest);
}
