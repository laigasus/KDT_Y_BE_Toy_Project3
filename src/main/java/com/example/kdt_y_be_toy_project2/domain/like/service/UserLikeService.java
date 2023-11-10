package com.example.kdt_y_be_toy_project2.domain.like.service;

import com.example.kdt_y_be_toy_project2.domain.like.dto.*;

import java.util.List;

public interface UserLikeService {

    // 북마크 조회
    List<UserLikeGetTripsResponse> bringUserLike(long userId);

    // 북마크 추가
    UserLikeAddTripResponse addUserLike(UserLikeAddTripRequest userLikeAddTripRequest);

    // 북마크 삭제
    boolean removeUserLike(UserLikeRemoveTripRequest likeRemoveTripRequest);
}
