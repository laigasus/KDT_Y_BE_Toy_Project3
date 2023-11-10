package com.example.kdt_y_be_toy_project2.domain.like.repository;

import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>{
    boolean deleteByUserEmailAndTripTripId(String email, Long tripId);

}
