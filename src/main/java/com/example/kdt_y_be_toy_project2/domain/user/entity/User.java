package com.example.kdt_y_be_toy_project2.domain.user.entity;

import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("회원")
public class User extends BaseTimeEntity implements UserDetails {

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

    @OneToMany(mappedBy = "user")
    private final List<UserLike> userLikes = new ArrayList<>();

    @Builder
    public User(String email, String username, String password,String authority) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    private String authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    public Boolean isAdmin() {
        return authority.equals("ROLE_ADMIN");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
