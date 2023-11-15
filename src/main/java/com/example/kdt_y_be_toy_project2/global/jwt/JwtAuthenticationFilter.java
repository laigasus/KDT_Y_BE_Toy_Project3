package com.example.kdt_y_be_toy_project2.global.jwt;

import com.example.kdt_y_be_toy_project2.domain.user.dto.LoginRequest;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JWT를 이용한 로그인 인증
 */
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProvider jwtProvider;
    UserRepository userRepository;

    CustomAuthenticationFailureHandler authenticationFailureHandler;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtProvider jwtProvider,
            UserRepository userRepository,
            CustomAuthenticationFailureHandler authenticationFailureHandler
    ) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        setAuthenticationFailureHandler(authenticationFailureHandler);
        super.setAuthenticationManager(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
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

            Optional<User> user = userRepository.findByEmail(loginRequest.email());

            if (user.isEmpty()) {
                throw new BadCredentialsException("이메일 또는 비밀번호가 잘못되었습니다.");
            }

            // 로그인할 때 입력한 email과 password를 가지고 authenticationToken를 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password(),
                    new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_USER")))
            );

            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationException e){
            try {
                //로그인시 발생한 에러를 unsuccessfulAuthentication에서 처리
                unsuccessfulAuthentication(request, response, e);
            } catch (IOException | ServletException ex) {
                throw new RuntimeException(ex);
            }
            return null;
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
    ) throws IOException, ServletException {
        //super.unsuccessfulAuthentication(request, response, failed);
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }
}