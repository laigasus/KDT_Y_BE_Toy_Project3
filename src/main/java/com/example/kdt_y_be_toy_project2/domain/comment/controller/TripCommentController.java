package com.example.kdt_y_be_toy_project2.domain.comment.controller;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.TripCommentAddRequest;
import com.example.kdt_y_be_toy_project2.domain.comment.dto.TripCommentUpdateRequest;
import com.example.kdt_y_be_toy_project2.domain.comment.service.TripCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
            // TODO : jwt토큰에서 userid를 받는 방식으로 교체필요
            @RequestParam long userid
    ) {
        return ResponseEntity.ok(tripCommentService.insertTripComment(tripId, tripCommentAddRequest,userid));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> editComment(
            @PathVariable long tripId,
            @PathVariable long commentId,
            @RequestBody @Valid TripCommentUpdateRequest commentUpdateRequest
    ) {
        return ResponseEntity.ok(tripCommentService.updateTripComment(tripId, commentId, commentUpdateRequest));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable long tripId,
            @PathVariable long commentId
    ) {
        tripCommentService.deleteTripComment(tripId, commentId);
        return ResponseEntity.noContent().build();
    }
}
