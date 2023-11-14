package com.example.kdt_y_be_toy_project2.domain.comment.service;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;
import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidTripException;
import com.example.kdt_y_be_toy_project2.domain.comment.repository.TripCommentRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import com.example.kdt_y_be_toy_project2.global.exception.InvalidPrincipalDetailsException;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        checkUserLogined(principalDetails);

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
        checkUserLogined(principalDetails);

        //TODO : user.id 와 댓글 작성자가 일치하는지 확인
        return tripCommentRepository.findById(commentId).map(tripComment -> {
            tripComment.editTripComment(commentUpdateRequest);
            return TripCommentUpdateResponse.fromEntity(tripComment);
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean deleteTripComment(
            long tripId,
            long commentId,
            PrincipalDetails principalDetails
    ) {
        checkUserLogined(principalDetails);

        tripCommentRepository.deleteById(commentId);
        return true;
    }

    private static void checkUserLogined(PrincipalDetails principalDetails) {
        if(principalDetails ==null){
            throw new InvalidPrincipalDetailsException();
        }
    }
}
