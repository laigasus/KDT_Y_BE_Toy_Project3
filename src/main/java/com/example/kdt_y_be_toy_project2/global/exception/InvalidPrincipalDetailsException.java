package com.example.kdt_y_be_toy_project2.global.exception;

public class InvalidPrincipalDetailsException extends GlobalException {
    public InvalidPrincipalDetailsException() {
        super("유저를 인증할 수 없습니다.");
    }
}
