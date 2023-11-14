package com.example.kdt_y_be_toy_project2.domain.user.service;

import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserResponse;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public CreateUserResponse signup(CreateUserRequest createUserRequest) {

        return CreateUserResponse.fromEntity(
                userRepository.save(createUserRequest.toEntity()));
    }
}
