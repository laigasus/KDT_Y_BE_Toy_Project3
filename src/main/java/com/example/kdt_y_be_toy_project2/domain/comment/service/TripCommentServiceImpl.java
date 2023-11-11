package com.example.kdt_y_be_toy_project2.domain.comment.service;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;
import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidTripException;
import com.example.kdt_y_be_toy_project2.domain.comment.repository.TripCommentRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class TripCommentServiceImpl implements TripCommentService{
    private final TripCommentRepository tripCommentRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Override
    public List<TripCommentGetResponse> bringTripComments(long tripId){
        return tripCommentRepository.findByTripTripId(tripId).stream()
                .map(TripCommentGetResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public TripCommentAddResponse insertTripComment(
            long tripId,
            TripCommentAddRequest tripCommentAddRequest,
            long userid
    ){
        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        if(tripOptional.isPresent()){
            return TripCommentAddResponse.fromEntity(
                    tripCommentRepository.save(tripCommentAddRequest.toEntity(
                            userRepository.findByUserId(userid),tripOptional.get())
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
            TripCommentUpdateRequest commentUpdateRequest
    ){
        return tripCommentRepository.findById(commentId).map(tripComment->{
            tripComment.editTripComment(commentUpdateRequest);
            return TripCommentUpdateResponse.fromEntity(tripComment);
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean deleteTripComment(
            long tripId,
            long commentId
    ){
        tripCommentRepository.deleteById(commentId);

        return true;
    }
}
