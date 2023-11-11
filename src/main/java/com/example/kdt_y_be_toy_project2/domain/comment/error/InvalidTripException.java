package com.example.kdt_y_be_toy_project2.domain.comment.error;
import com.example.kdt_y_be_toy_project2.global.exception.GlobalException;

public class InvalidTripException extends GlobalException {
    public InvalidTripException() {
        super("존재하지 않는 여행입니다.");
    }
}
