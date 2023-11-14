package com.example.kdt_y_be_toy_project2.domain.like.controller;

import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeAddTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeRemoveTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.service.UserLikeService;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userLike")
@RequiredArgsConstructor
public class UserLikeController {
    private final UserLikeService userLikeService;

    @PostMapping("")
    public ResponseEntity<?> addUserLike(
            @RequestBody @Valid UserLikeAddTripRequest userLikeAddTripRequest,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return ResponseEntity.ok(userLikeService.addUserLike(principalDetails, userLikeAddTripRequest.tripId()));
    }

    @GetMapping("")
    public ResponseEntity<?> getUserLike(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(userLikeService.bringUserLike(principalDetails));
    }

    @DeleteMapping("")
    public ResponseEntity<?> removeUserLike(
            @RequestBody @Valid UserLikeRemoveTripRequest userLikeRemoveTripRequest,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return ResponseEntity.ok(userLikeService.removeUserLike(principalDetails, userLikeRemoveTripRequest.tripId()));
    }
}
