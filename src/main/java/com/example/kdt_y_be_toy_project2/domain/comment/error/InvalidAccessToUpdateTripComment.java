package com.example.kdt_y_be_toy_project2.domain.comment.error;

import com.example.kdt_y_be_toy_project2.global.exception.GlobalException;

public class InvalidAccessToUpdateTripComment extends GlobalException {
    public InvalidAccessToUpdateTripComment() {
        super("댓글을 수정할 수 있는 권한이 없습니다. 댓글 작성자가 아닙니다.");
    }
}
