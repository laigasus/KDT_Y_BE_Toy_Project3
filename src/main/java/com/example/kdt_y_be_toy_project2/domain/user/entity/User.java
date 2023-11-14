package com.example.kdt_y_be_toy_project2.domain.user.entity;

import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("회원")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 번호(PK)")
    private Long userId;

    @Comment("회원 이메일")
    private String email;

    @Comment("회원 이름")
    private String username;

    @Comment("회원 비밀번호")
    private String password;

    @Builder
    public User(String email, String username, String password, String authority) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    private String authority;
}
