package com.xml.agentback.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CarRating {

    private Long id;

    private Double rating;
    private User user;
    private Car car;
    private String comment;
    private RatingStatus ratingStatus;

    public CarRating() {
    }
}
