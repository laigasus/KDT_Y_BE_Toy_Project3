package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transport;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.TransportEnum;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record TransportDTO(
        @Schema(example = "버스")
        TransportEnum transportEnum,

        @Schema(name = "출발지", example = "서울")
        String departurePlace,

        @Schema(name = "도착지", example = "속초")
        String arrivalPlace,

        @Schema(name = "출발 시각")
        String departureTime,

        @Schema(name = "도착 시각")
        String arrivalTime

) {
    private static TransportDTO fromEntity(Transport transport) {
        return new TransportDTO(
                transport.getTransportEnum(),
                transport.getDeparturePlace(),
                transport.getArrivalPlace(),
                TimeUtils.formatDateTime(transport.getTransportTimeSchedule().getStartTime()),
                TimeUtils.formatDateTime(transport.getTransportTimeSchedule().getEndTime())
        );
    }

    public static List<TransportDTO> fromEntities(List<Transport> transports) {
        return transports.stream().map(TransportDTO::fromEntity).toList();
    }
}