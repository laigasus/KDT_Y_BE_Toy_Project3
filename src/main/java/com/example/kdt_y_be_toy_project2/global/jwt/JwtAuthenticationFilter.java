package com.example.kdt_y_be_toy_project2.global.jwt;

import com.example.kdt_y_be_toy_project2.domain.user.dto.LoginRequest;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT를 이용한 로그인 인증
 */
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtProvider jwtProvider
    ) {
        super.setAuthenticationManager(authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    /**
     * 로그인 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            // 요청된 JSON 데이터를 객체로 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            // 로그인할 때 입력한 email과 password를 가지고 authenticationToken를 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password(),
                    new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_USER")))
            );

            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 인증에 성공했을 때 사용
     * JWT Token을 생성해서 쿠키에 넣는다.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        User user = ((PrincipalDetails) authResult.getPrincipal()).getUser();
        String token = jwtProvider.createToken(user);
        // 쿠키 생성
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, token);
        cookie.setMaxAge(JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME / 1000 * 2); // 쿠키의 만료시간은 jwt토큰의 만료시간보다 김 -> setMaxAge는 초단위
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/");  //발급후 redirect로 이동
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException {
        response.sendError(
                HttpStatus.BAD_REQUEST.value(),
                "JwtAuthenticationFilter에서 인증 실패"
        );
    }
}