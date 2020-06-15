package com.xml.agentback.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRatingDTO {
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
}
