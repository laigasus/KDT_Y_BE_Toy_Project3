package com.example.kdt_y_be_toy_project2.domain.comment.entity;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.TripCommentUpdateRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TripComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("여행 리뷰 번호(PK)")
    private Long tripCommentId;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonIgnore
    private Trip trip;

    @Comment("리뷰")
    private String tripComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private TripComment(Trip trip, Long tripCommentId, String tripComment, User user) {
        this.trip = trip;
        this.tripCommentId = tripCommentId;
        this.tripComment = tripComment;
        this.user = user;
    }

    public void editTripComment(TripCommentUpdateRequest tripCommentUpdateRequest){
        this.tripComment=tripCommentUpdateRequest.tripComment();
    }
}
