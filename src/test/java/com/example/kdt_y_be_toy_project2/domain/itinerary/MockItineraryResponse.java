package com.example.kdt_y_be_toy_project2.domain.itinerary;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.AccommodationDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ActivityDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ResidenceDTO;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;

import java.util.List;

public class MockItineraryResponse {

    public ItineraryResponse create() {

        List<AccommodationDTO> mockAccommodations = List.of(
                new AccommodationDTO(
                        "신라 스테이 해운대 수정test",
                        "2023-10-27T15:00:00",
                        "2023-10-28T18:00:00"
                )

        );


        List<ResidenceDTO> mockResidences = List.of(
                new ResidenceDTO(
                        "부산",
                        "2023-10-27T15:00:00",
                        "2023-10-28T18:00:00"
                )

        );


        List<ActivityDTO> mockActivities = List.of(
                new ActivityDTO(
                        "KTX",
                        "집",
                        "부산역",
                        "KTX타고 드디어 부산입성!",
                        "2023-01-02T14:00:00",
                        "2023-01-03T11:00:00",
                        "부산역"
                ),
                new ActivityDTO(
                        "CAR",
                        "부산역",
                        "해운대 블루라인파크",
                        "스카이캡슐 타서 경치보기",
                        "2023-10-27T13:20:00",
                        "2023-10-27T14:00:00",
                        "해운대 블루라인파크"
                )

        );


        TimeScheduleDTO mockTimeSchedule = new TimeScheduleDTO(
                "2023-10-28T13:05:00",
                "2023-10-28T11:15:00"
        );


        return new ItineraryResponse(
                7L,
                "부산 수정test",
                1L,
                mockAccommodations,
                mockResidences,
                mockActivities,
                mockTimeSchedule,
                "2023-01-01T00:00:00",
                "2023-01-02T00:00:00"
        );
    }
}
