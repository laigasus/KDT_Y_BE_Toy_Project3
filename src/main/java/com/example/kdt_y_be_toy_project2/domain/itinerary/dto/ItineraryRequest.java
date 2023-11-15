package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Activity;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import com.example.kdt_y_be_toy_project2.global.util.api.service.GoogleMapUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO for {@link Itinerary}
 */
public record ItineraryRequest(
        @Schema(example = "옥크나이트와 떠나는 코딩숙박")
        String itineraryName,

        List<Accommodation> accommodation,

        List<Residence> residence,
        List<Activity> activity,
        TimeSchedule timeSchedule
) {
    public Itinerary toEntity() {
        Itinerary itinerary = Itinerary.builder()
                .itineraryName(itineraryName)
                .residences(residence)
                .accommodations(accommodation)
                .activities(activity)
                .timeSchedule(timeSchedule)
                .build();

        // ArrivalPlace에 대한 주소 정보 추가
        setArrivalAddresses(itinerary.getActivity());
        return itinerary;
    }

    private void setArrivalAddresses(List<Activity> activities) {
        for (Activity activity : activities) {
            String arrivalPlace = activity.getArrivalPlace();
            String arrivalAddress = GoogleMapUtils.getAddress(arrivalPlace);
            activity.setArrivalAddress(arrivalAddress);
        }
    }
}
