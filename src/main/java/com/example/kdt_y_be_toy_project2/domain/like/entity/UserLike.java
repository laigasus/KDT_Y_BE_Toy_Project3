package com.example.kdt_y_be_toy_project2.domain.like.entity;

import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    public static UserLike click(Trip trip, User user){
        UserLike userLike = new UserLike();
        userLike.setTrip(trip);
        userLike.setUser(user);

        return userLike;
    }

    public void setTrip(Trip trip){
        this.trip = trip;
        trip.getUserLikes().add(this);
    }

    public void setUser(User user){
        this.user = user;
        user.getUserLikes().add(this);
    }

    @Builder
    private UserLike(Trip trip, User user) {
        this.trip = trip;
        this.user = user;
    }


}
