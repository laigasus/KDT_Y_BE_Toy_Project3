package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transport;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.TransportEnum;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;

import java.util.List;

public record TransportDTO(
        TransportEnum transportEnum,
        String departurePlace,
        String arrivalPlace,
        String departureTime,
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