package com.xml.agentback.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Advertisment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "advertiser_id")
    private User advertiser;

    @Column
    private LocalDateTime availableFrom;

    @Column
    private LocalDateTime availableTo;

    public Advertisment() {
    }

    public Advertisment(Car car, User advertiser, LocalDateTime availableFrom, LocalDateTime availableTo) {
        this.car = car;
        this.advertiser = advertiser;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }
}
