package com.example.kdt_y_be_toy_project2.global.dummy;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.comment.repository.TripCommentRepository;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.repository.ItineraryRepository;
import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.like.repository.UserLikeRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DummyDevInit extends DummyObjectForRepository {
    @Profile("test")
    @Bean
    CommandLineRunner init(
            UserRepository userRepository,
            TripRepository tripRepository,
            ItineraryRepository itineraryRepository,
            TripCommentRepository tripCommentRepository,
            UserLikeRepository userLikeRepository
    ) {
        return (args) -> {
            User user1 = userRepository.save(dummyUser());
            Trip trip1 = tripRepository.save(dummyTrip());
            Itinerary itinerary1 = itineraryRepository.save(dummyItinerary(trip1));
            TripComment tripComment1 = tripCommentRepository.save(dummyTripComment(trip1, user1));
            UserLike userLike1 = userLikeRepository.save(dummyUserLike(user1, trip1));
        };
    }
}
