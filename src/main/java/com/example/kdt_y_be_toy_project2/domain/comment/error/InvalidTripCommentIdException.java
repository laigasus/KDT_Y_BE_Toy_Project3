package com.example.kdt_y_be_toy_project2.domain.comment.error;
import com.example.kdt_y_be_toy_project2.global.exception.GlobalException;

public class InvalidTripCommentIdException extends GlobalException {
    public InvalidTripCommentIdException() {
        super("존재하지 않는 여행댓글입니다.");
    }
}
