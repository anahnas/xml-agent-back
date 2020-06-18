package com.xml.agentback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xml.agentback.DTO.CarRatingDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CarRating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Double rating;
    @ManyToOne
    private User user;
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Car car;
    private String comment;
    private RatingStatus ratingStatus;

    public CarRating() {
    }

    public CarRating(CarRatingDTO carRatingDTO) {
        this.id = carRatingDTO.getId();
        this.rating = carRatingDTO.getRating();
        this.user = new User(carRatingDTO.getUserDTO());
        System.out.println("input id: " + carRatingDTO.getCarDTO().getId());
        this.car = new Car(carRatingDTO.getCarDTO().getId());
        this.comment = carRatingDTO.getComment();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
