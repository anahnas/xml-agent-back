package com.xml.agentback.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarClassDTO {
    private String carClass;

    public CarClassDTO(){}

    public CarClassDTO(String carClass) {
        this.carClass = carClass;
    }
}
