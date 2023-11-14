package com.example.kdt_y_be_toy_project2.domain.like.controller;

import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeAddTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeRemoveTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.service.UserLikeService;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userLike")
@RequiredArgsConstructor
public class UserLikeController {
    private final UserLikeService userLikeService;

    @PostMapping("")
    public ResponseEntity<?> addUserLike(
            @RequestBody @Valid UserLikeAddTripRequest userLikeAddTripRequest,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userLikeService.addUserLike(user, userLikeAddTripRequest.tripId()));
    }


    @GetMapping("")
    public ResponseEntity<?> getUserLike(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userLikeService.bringUserLike(user));
    }

    @DeleteMapping("")
    public ResponseEntity<?> removeUserLike(
            @RequestBody @Valid UserLikeRemoveTripRequest userLikeRemoveTripRequest,
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();
        userLikeService.removeUserLike(user, userLikeRemoveTripRequest.tripId());
        return ResponseEntity.noContent().build();
    }

}
