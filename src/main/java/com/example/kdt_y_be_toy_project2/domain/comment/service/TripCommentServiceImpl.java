package com.example.kdt_y_be_toy_project2.domain.comment.service;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;
import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidAccessToUpdateTripComment;
import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidTripCommentIdException;
import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidTripException;
import com.example.kdt_y_be_toy_project2.domain.comment.repository.TripCommentRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import com.example.kdt_y_be_toy_project2.global.config.CheckUserLogined;
import com.example.kdt_y_be_toy_project2.global.exception.InvalidPrincipalDetailsException;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TripCommentServiceImpl implements TripCommentService {
    private final TripCommentRepository tripCommentRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Override
    public List<TripCommentGetResponse> bringTripComments(long tripId) {

        if (tripRepository.findById(tripId).isPresent()) {
            return tripCommentRepository.findByTripTripId(tripId).stream()
                    .map(TripCommentGetResponse::fromEntity)
                    .toList();
        } else {
            throw new InvalidTripException();
        }
    }

    @Override
    @Transactional
    public TripCommentAddResponse insertTripComment(
            long tripId,
            TripCommentAddRequest tripCommentAddRequest,
            PrincipalDetails principalDetails
    ) {
        CheckUserLogined.checkUserLogined(principalDetails);
        long userId=principalDetails.getUser().getUserId();

        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        if (tripOptional.isPresent()) {
            return TripCommentAddResponse.fromEntity(
                    tripCommentRepository.save(tripCommentAddRequest.toEntity(
                            userRepository.findByUserId(userId), tripOptional.get())
                    ));
        } else {
            throw new InvalidTripException();
        }
    }

    @Override
    @Transactional
    public TripCommentUpdateResponse updateTripComment(
            long tripId,
            long commentId,
            TripCommentUpdateRequest commentUpdateRequest,
            PrincipalDetails principalDetails
    ) {
        CheckUserLogined.checkUserLogined(principalDetails);

        Optional<TripComment> tripCommentOptional = tripCommentRepository.findById(commentId);

        if (tripCommentOptional.isPresent()) {
            TripComment tripComment = tripCommentOptional.get();

            if (!Objects.equals(principalDetails.getUser().getUserId(), tripComment.getUser().getUserId())) {
                throw new InvalidAccessToUpdateTripComment();
            }

            tripComment.editTripComment(commentUpdateRequest);
            return TripCommentUpdateResponse.fromEntity(tripComment);
        } else {
            throw new InvalidTripCommentIdException();
        }
    }

    @Override
    public boolean deleteTripComment(
            long tripId,
            long commentId,
            PrincipalDetails principalDetails
    ) {
        CheckUserLogined.checkUserLogined(principalDetails);
        tripCommentRepository.deleteById(commentId);
        return true;
    }
}
