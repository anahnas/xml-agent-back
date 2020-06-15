package com.xml.agentback.model;

import com.xml.agentback.DTO.CarRatingDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class CarRating {
    private Long id;

    private Double rating;
    private User user;
    private Car car;
    private String comment;
    private RatingStatus ratingStatus;

    public CarRating() {
    }

    public CarRating(CarRatingDTO carRatingDTO) {
        if(carRatingDTO.getId() != null)
            this.id = carRatingDTO.getId();
        this.rating = carRatingDTO.getRating();
        this.user = new User(carRatingDTO.getUserDTO());
        this.car = new Car(carRatingDTO.getCarDTO());
        this.comment = carRatingDTO.getComment();
    }
}
