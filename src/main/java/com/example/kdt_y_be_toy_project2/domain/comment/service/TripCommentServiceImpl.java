package com.example.kdt_y_be_toy_project2.domain.comment.service;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;
import com.example.kdt_y_be_toy_project2.domain.comment.repository.TripCommentRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class TripCommentServiceImpl implements TripCommentService{
    private final TripCommentRepository tripCommentRepository;
    private final TripRepository tripRepository;

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
            TripCommentAddRequest tripCommentAddRequest
    ){
        if(tripRepository.findById(tripId).isPresent()){
            return TripCommentAddResponse.fromEntity(
                    tripCommentRepository.save(tripCommentAddRequest.toEntity())
            );
        } else {
            throw new RuntimeException("실패!!!!!!");
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
