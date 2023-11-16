package com.example.kdt_y_be_toy_project2.global.config;

import com.example.kdt_y_be_toy_project2.global.jwt.JwtAuthenticationFilter;
import com.example.kdt_y_be_toy_project2.global.jwt.JwtAuthorizationFilter;
import com.example.kdt_y_be_toy_project2.global.util.CustomResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        http.httpBasic(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.rememberMe(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        ).addFilterBefore(
                jwtAuthorizationFilter,
                BasicAuthenticationFilter.class
        );

        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers(new AntPathRequestMatcher("/trip/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/itinerary/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/user/signup")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/trip/{tripId}/comments")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/places/search/**")).permitAll()
                        .anyRequest().authenticated()
        );

        http.exceptionHandling(exceptionHandling -> {
            exceptionHandling.authenticationEntryPoint((request, response, authException) ->
                    CustomResponseUtil.fail(response, "로그인을 진행해 주세요", HttpStatus.UNAUTHORIZED));

            exceptionHandling.accessDeniedHandler((request, response, accessDeniedException) ->
                    CustomResponseUtil.fail(response, "접근 권한이 없습니다", HttpStatus.FORBIDDEN));
        });

        return http.build();
    }
}