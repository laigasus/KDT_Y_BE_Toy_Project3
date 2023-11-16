package com.example.kdt_y_be_toy_project2.domain.like.service;

import com.example.kdt_y_be_toy_project2.domain.like.dto.DeleteUserLikeResponse;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeAddTripResponse;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeGetTripsResponse;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;

import java.util.List;

public interface UserLikeService {

    List<UserLikeGetTripsResponse> bringUserLike(PrincipalDetails principalDetails);

    UserLikeAddTripResponse addUserLike(PrincipalDetails principalDetails, Long tripId);

    DeleteUserLikeResponse removeUserLike(PrincipalDetails principalDetails, Long tripId);
}
