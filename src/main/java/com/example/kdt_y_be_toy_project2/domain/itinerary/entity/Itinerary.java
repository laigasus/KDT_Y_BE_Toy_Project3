package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("여정")
public class Itinerary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("여정 id(PK)")
    private Long itineraryId;

    @Comment("여정 이름")
    private String itineraryName;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonIgnore
    private Trip trip;

    @ElementCollection
    @CollectionTable(name = "accommodation", joinColumns = @JoinColumn(name = "itinerary_id"))
    private List<Accommodation> accommodation = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "residence", joinColumns = @JoinColumn(name = "itinerary_id"))
    private List<Residence> residence = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "activity", joinColumns = @JoinColumn(name = "itinerary_id"))
    private List<Activity> activity = new ArrayList<>();

    @Embedded
    protected TimeSchedule timeSchedule;

    @Builder
    private Itinerary(Long itineraryId, String itineraryName, Trip trip, List<Accommodation> accommodations, List<Residence> residences, List<Activity> activities, TimeSchedule timeSchedule) {
        this.itineraryId = itineraryId;
        this.itineraryName = itineraryName;
        this.trip = trip;
        this.accommodation = accommodations;
        this.residence = residences;
        this.activity = activities;
        this.timeSchedule = timeSchedule;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
        trip.getItineraries().add(this);
    }

    public void modifyInfo(ItineraryRequest itineraryRequest) {
        this.residence = itineraryRequest.residence();
        this.accommodation = itineraryRequest.accommodation();
        this.activity = itineraryRequest.activity();
    }
}
