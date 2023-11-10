package com.example.kdt_y_be_toy_project2.domain.like.controller;

import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeAddTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeRemoveTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.service.UserLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userlike")
@RequiredArgsConstructor
public class UserLikeController {
    private final UserLikeService userLikeService;

    @PostMapping("")
    public ResponseEntity<?> addUserLike(
            @RequestBody @Valid UserLikeAddTripRequest userLikeAddTripRequest
            //TODO: jwt에서 userId가져와서 같이 넣어줘야함
    ){
        return ResponseEntity.ok(userLikeService.addUserLike(userLikeAddTripRequest));
    }


    @GetMapping("{userId}")
    public ResponseEntity<?> getUserLike(@PathVariable final long userId){
        return ResponseEntity.ok(userLikeService.bringUserLike(userId));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> removeUserLike(
            @RequestBody @Valid UserLikeRemoveTripRequest userLikeRemoveTripRequest
    ){
        userLikeService.removeUserLike(userLikeRemoveTripRequest);
        return ResponseEntity.noContent().build();
    }

}
