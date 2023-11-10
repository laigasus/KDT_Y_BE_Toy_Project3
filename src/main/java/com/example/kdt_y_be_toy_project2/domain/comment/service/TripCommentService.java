package com.example.kdt_y_be_toy_project2.domain.comment.service;


import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;

import java.util.List;

public interface TripCommentService {
    // 댓글 조회
    List<TripCommentGetResponse> bringTripComments(long tripId);

    // 댓글 추가
    TripCommentAddResponse insertTripComment(long tripId, TripCommentAddRequest tripCommentAddRequest);

    // 댓글 수정
    TripCommentUpdateResponse updateTripComment(long tripId, long commentId, TripCommentUpdateRequest commentUpdateRequest);

    // 댓글 삭제
    boolean deleteTripComment(long tripId, long commentId);
}
