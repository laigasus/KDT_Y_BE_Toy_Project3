package com.example.kdt_y_be_toy_project2.domain.user.service;

import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserResponse;
import com.example.kdt_y_be_toy_project2.domain.user.error.EmailDuplicateError;
import com.example.kdt_y_be_toy_project2.domain.user.error.InvalidEmailException;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import com.example.kdt_y_be_toy_project2.global.dummy.DummyObjectForController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class UserServiceTest extends DummyObjectForController {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void signupSuccessTest() {
        // Given
        CreateUserRequest request = dummyCreateUserRequest();
        given(userRepository.findByEmail(any(String.class))).willReturn(Optional.empty());
        given(userRepository.save(any())).willReturn(request.toEntity());

        // When
        CreateUserResponse response = userService.signup(request);

        // Then
        assertNotNull(response);
        assertEquals(request.email(), response.email());
    }

    @ParameterizedTest
    @DisplayName("올바르지 않은 이메일로 인한 InvalidEmailException 발생")
    @MethodSource("provideInvalidEmailAndPassword")
    void signupFailureTest(String email,String username ,String password) {
        // Given
        CreateUserRequest request = new CreateUserRequest(email, username, password);

        // Then
        assertThrows(InvalidEmailException.class, () -> userService.signup(request));
    }

    private static Stream<Arguments> provideInvalidEmailAndPassword() {
        return Stream.of(
                Arguments.of("test$Test!!!Gmal.com","kingking" ,"123123123"),
                Arguments.of("jaxKinaver.com","qweqw" ,"j123312"),
                Arguments.of("qwe$qwe$naver.com","123" ,"asdu2312"),
                Arguments.of("King!King$gmail.com","asdjzxc" , "1231237832")
        );
    }



    @Test
    @DisplayName("중복된 이메일로 인한 로그인 실패")
    void signupFailureDueToDuplicateEmailTest() {
        // Given
        CreateUserRequest request = dummyCreateUserRequest();
        given(userRepository.findByEmail(any(String.class))).willReturn(Optional.of(request.toEntity()));

        // When & Then
        assertThrows(EmailDuplicateError.class, () -> userService.signup(request));
    }

}
