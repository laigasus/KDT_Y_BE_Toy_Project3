package com.example.kdt_y_be_toy_project2.domain.user.controller;

import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.LoginRequest;
import com.example.kdt_y_be_toy_project2.domain.user.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.signup(createUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.login(loginRequest));
    }




}
