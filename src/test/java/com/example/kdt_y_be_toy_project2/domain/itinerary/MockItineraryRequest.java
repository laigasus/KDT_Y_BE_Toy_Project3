package com.example.kdt_y_be_toy_project2.domain.itinerary;


import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.AccommodationDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ActivityDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ResidenceDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Activity;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.TransportEnum;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;

import java.time.LocalDateTime;
import java.util.List;

public class MockItineraryRequest {

    public ItineraryRequest create() {
        List<Accommodation> accommodations = List.of(
                Accommodation.builder()
                        .accommodationName("신라 스테이 해운대 수정test")
                        .accommodationTimeSchedule(new TimeSchedule(
                                LocalDateTime.parse("2023-10-27T15:00:00"),
                                LocalDateTime.parse("2023-10-28T18:00:00")))
                        .build()

        );

        List<Residence> residences = List.of(
                Residence.builder()
                        .residenceName("부산")
                        .residenceTimeSchedule(new TimeSchedule(
                                LocalDateTime.parse("2023-10-27T15:00:00"),
                                LocalDateTime.parse("2023-10-28T18:00:00")))
                        .build()

        );


        List<Activity> activities = List.of(
                Activity.builder()
                        .transportEnum(TransportEnum.KTX)
                        .departurePlace("집")
                        .arrivalPlace("부산역")
                        .description("KTX타고 드디어 부산입성!")
                        .activityTimeSchedule(new TimeSchedule(
                                LocalDateTime.parse("2023-01-02T14:00:00"),
                                LocalDateTime.parse("2023-01-03T11:00:00")))
                        .build(),

                Activity.builder()
                        .transportEnum(TransportEnum.CAR)
                        .departurePlace("부산역")
                        .arrivalPlace("해운대 블루라인파크")
                        .description("스카이캡슐 타서 경치보기")
                        .activityTimeSchedule(new TimeSchedule(
                                LocalDateTime.parse("2023-10-27T13:20:00"),
                                LocalDateTime.parse("2023-10-27T14:00:00")))
                        .build()

        );

        return new ItineraryRequest(
                "부산 수정test",
                accommodations,
                residences,
                activities,
                new TimeSchedule(
                        LocalDateTime.parse("2023-10-28T13:05:00"),
                        LocalDateTime.parse("2023-10-28T11:15:00"))
        );
    }
}

