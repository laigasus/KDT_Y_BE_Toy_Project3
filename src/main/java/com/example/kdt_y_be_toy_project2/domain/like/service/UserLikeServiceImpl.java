package com.example.kdt_y_be_toy_project2.domain.like.service;

import com.example.kdt_y_be_toy_project2.domain.like.dto.DeleteUserLikeResponse;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeAddTripResponse;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeGetTripsResponse;
import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.like.repository.UserLikeRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.error.TripNotLoadedException;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.global.config.CheckUserLogined;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLikeServiceImpl implements UserLikeService {

    private final UserLikeRepository userLikeRepository;
    private final TripRepository tripRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserLikeGetTripsResponse> bringUserLike(PrincipalDetails principalDetails) {

        return userLikeRepository.findByUser(principalDetails.getUser()).stream()
                .map(UserLikeGetTripsResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public UserLikeAddTripResponse addUserLike(PrincipalDetails principalDetails, Long tripId) {

        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        CheckUserLogined.checkUserLogined(principalDetails);

        if (tripOptional.isEmpty()) {
            throw new TripNotLoadedException();
        }
        UserLike like = UserLike.like(principalDetails.getUser(), tripOptional.get());

        Optional<UserLike> userLikeOptional = userLikeRepository.findByUserAndTripTripId(principalDetails.getUser(), tripId);
        if (userLikeOptional.isPresent()) {
            return new UserLikeAddTripResponse(
                    like.getTrip().getTripId(),
                    like.getUser().getUserId(),
                    "이미 해당 여행에 좋아요가 생성 되어 있습니다.");
        }

        return UserLikeAddTripResponse.fromEntity(userLikeRepository.save(like), tripId + "번 여행 좋아요 요청 성공");
    }

    @Override
    @Transactional
    public DeleteUserLikeResponse removeUserLike(PrincipalDetails principalDetails, Long tripId) {

        Optional<UserLike> userLike = userLikeRepository.findByUserAndTripTripId(principalDetails.getUser(), tripId);

        if (userLike.isEmpty()) {
            return DeleteUserLikeResponse.fromEntity("해당 여행에 등록된 좋아요가 없으므로 삭제할 좋아요가 없습니다.");
        }

        if (!Objects.equals(userLike.get().getUser().getUserId(), principalDetails.getUser().getUserId())) {
            return DeleteUserLikeResponse.fromEntity(
                    "좋아요를 해제할 권한이 없습니다 -> 좋아요를 등록한 사용자가 아닙니다.");
        }

        userLike.get().delete();
        userLikeRepository.delete(userLike.get());
        return DeleteUserLikeResponse.fromEntity("좋아요 삭제 성공");
    }
}
