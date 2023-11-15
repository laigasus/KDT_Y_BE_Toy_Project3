package com.example.kdt_y_be_toy_project2.domain.like.entity;

import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity
@Comment("좋아요")
public class UserLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("여행 좋아요 번호")
    private Long userLikeId;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 좋아요 클릭
    public static UserLike like(User user, Trip trip) {
        UserLike userLike = new UserLike();
        userLike.setTripAndUser(trip, user);

        return userLike;
    }

    // Trip과 User에 해당하는 UserLike 연관관계 삭제
    public void delete() {
        this.trip.getUserLikes().remove(this);
    }

    public void setTripAndUser(Trip trip, User user) {
        this.trip = trip;
        this.user = user;

        trip.getUserLikes().add(this);
    }

    @Builder
    private UserLike(Trip trip, User user) {
        this.trip = trip;
        this.user = user;
    }
}
