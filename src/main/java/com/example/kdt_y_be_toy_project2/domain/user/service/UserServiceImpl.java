package com.example.kdt_y_be_toy_project2.domain.user.service;

import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserResponse;
import com.example.kdt_y_be_toy_project2.domain.user.error.EmailDuplicateError;
import com.example.kdt_y_be_toy_project2.domain.user.error.InvalidEmailException;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public CreateUserResponse signup(CreateUserRequest createUserRequest) {

        if (!isValidEmail(createUserRequest.email())) {
            throw new InvalidEmailException();
        }

        if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
            throw new EmailDuplicateError();
        }

        return CreateUserResponse.fromEntity(
                userRepository.save(createUserRequest.toEntity()));
    }

    private static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
