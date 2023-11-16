package com.example.kdt_y_be_toy_project2.domain.comment.service;


import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;

import java.util.List;

public interface TripCommentService {
    List<TripCommentGetResponse> bringTripComments(long tripId);

    TripCommentAddResponse insertTripComment(long tripId, TripCommentAddRequest tripCommentAddRequest, PrincipalDetails principalDetails);

    TripCommentUpdateResponse updateTripComment(long tripId, long commentId, TripCommentUpdateRequest commentUpdateRequest, PrincipalDetails principalDetails);

    boolean deleteTripComment(long tripId, long commentId, PrincipalDetails principalDetails);
}
