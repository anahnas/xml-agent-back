package com.xml.agentback.model;

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
    private Car car;
    private String comment;
    private RatingStatus ratingStatus;

    public CarRating() {
    }

    public CarRating(CarRatingDTO carRatingDTO) {
        System.out.println(carRatingDTO.getId() + " " + carRatingDTO.getUserDTO().getId());
        this.id = carRatingDTO.getId();
        this.rating = carRatingDTO.getRating();
        this.user = new User(carRatingDTO.getUserDTO().getId());
        this.car = new Car(carRatingDTO.getCarDTO().getId());
        this.comment = carRatingDTO.getComment();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
