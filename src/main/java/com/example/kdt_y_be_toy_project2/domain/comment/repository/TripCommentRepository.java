package com.example.kdt_y_be_toy_project2.domain.comment.repository;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripCommentRepository extends JpaRepository<TripComment, Long> {
    List<TripComment> findByTripTripId(long tripId);
}
