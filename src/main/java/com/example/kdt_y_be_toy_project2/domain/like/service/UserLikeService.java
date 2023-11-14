package com.example.kdt_y_be_toy_project2.domain.like.service;

import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeGetTripsResponse;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;

import java.util.List;

public interface UserLikeService {

    List<UserLikeGetTripsResponse> bringUserLike(User user);

    Long addUserLike(User user, Long tripId);

    void removeUserLike(User user, Long tripId);
}
