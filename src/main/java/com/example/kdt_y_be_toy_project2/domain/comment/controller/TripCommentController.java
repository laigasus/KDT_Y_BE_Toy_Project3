package com.example.kdt_y_be_toy_project2.domain.comment.controller;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.TripCommentAddRequest;
import com.example.kdt_y_be_toy_project2.domain.comment.dto.TripCommentUpdateRequest;
import com.example.kdt_y_be_toy_project2.domain.comment.service.TripCommentService;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip/{tripId}/comments")
@RequiredArgsConstructor
public class TripCommentController {
    private final TripCommentService tripCommentService;

    @GetMapping("")
    public ResponseEntity<?> bringComments(@PathVariable long tripId) {
        return ResponseEntity.ok(tripCommentService.bringTripComments(tripId));
    }

    @PostMapping("")
    public ResponseEntity<?> addComment(
            @PathVariable long tripId,
            @RequestBody @Valid TripCommentAddRequest tripCommentAddRequest,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
            return ResponseEntity.ok(tripCommentService.insertTripComment(
                    tripId,
                    tripCommentAddRequest,
                    principalDetails
            ));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> editComment(
            @PathVariable long tripId,
            @PathVariable long commentId,
            @RequestBody @Valid TripCommentUpdateRequest commentUpdateRequest,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return ResponseEntity.ok(tripCommentService.updateTripComment(
                tripId,
                commentId,
                commentUpdateRequest,
                principalDetails
        ));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable long tripId,
            @PathVariable long commentId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        tripCommentService.deleteTripComment(tripId, commentId, principalDetails);
        return ResponseEntity.ok().body(commentId+"번 댓글 삭제 성공");
    }
}
