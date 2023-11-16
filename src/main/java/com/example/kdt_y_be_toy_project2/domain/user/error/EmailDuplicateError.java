package com.example.kdt_y_be_toy_project2.domain.user.error;

public class EmailDuplicateError extends IllegalArgumentException {

    public EmailDuplicateError() {
        super("이미 해당 email로 가입한 유저가 있습니다 다른 email을 사용해야 합니다.");
    }
}
