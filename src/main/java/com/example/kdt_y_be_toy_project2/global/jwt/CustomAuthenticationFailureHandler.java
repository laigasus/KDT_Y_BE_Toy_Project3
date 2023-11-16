package com.example.kdt_y_be_toy_project2.global.jwt;

import com.example.kdt_y_be_toy_project2.global.exception.AuthenticationErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {

        logger.error("Authentication failed", exception);
        try {

            AuthenticationErrorMessage authenticationErrorMessage;

            if (exception instanceof BadCredentialsException) {
                authenticationErrorMessage = new AuthenticationErrorMessage("이메일 또는 비밀번호 에러");
            } else if (exception instanceof UsernameNotFoundException) {
                authenticationErrorMessage = new AuthenticationErrorMessage("Username not found");
            } else {
                authenticationErrorMessage = new AuthenticationErrorMessage("Authentication failed");
            }

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonErrorMessage = objectMapper.writeValueAsString(authenticationErrorMessage);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(jsonErrorMessage);

        } catch (Exception e) {
            logger.error("Error handling authentication failure", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write("Authentication failed: " + e.getMessage());
        }
    }
}
