package com.xml.agentback.DTO;

import com.xml.agentback.model.CarRating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRatingDTO {
    private Long id;
    private Double rating;
    private UserDTO userDTO;
    private CarDTO carDTO;
    private String comment;

    public CarRatingDTO() {
    }

    public CarRatingDTO(Double rating, UserDTO userDTO, CarDTO carDTO, String comment) {
        this.rating = rating;
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.comment = comment;
    }

    public CarRatingDTO(CarRating carRating) {
        this.id = carRating.getId();
        this.rating = carRating.getRating();
        this.userDTO = new UserDTO(carRating.getUser());
        this.carDTO = new CarDTO(carRating.getCar());
        this.comment = carRating.getComment();
    }
}
