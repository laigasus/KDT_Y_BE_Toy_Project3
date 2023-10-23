package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Itinerary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_id", updatable = false, nullable = false)
    @Comment("여정 번호(PK)")
    private Long id;

    @Embedded
    private Residence residence;

    @Embedded
    private Accommodation accommodation;

    @Embedded
    private Transportation transportation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    private Trip trip;

    @Builder
    private Itinerary(Long id, Residence residence, Accommodation accommodation, Transportation transportation) {
        this.id = id;
        this.residence = residence;
        this.accommodation = accommodation;
        this.transportation = transportation;
    }
}
