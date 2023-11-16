package com.example.kdt_y_be_toy_project2.domain.user.error;

public class InvalidEmailException extends IllegalArgumentException {

    public InvalidEmailException() {
        super("올바르지 않은 이메일 형식");
    }
}
