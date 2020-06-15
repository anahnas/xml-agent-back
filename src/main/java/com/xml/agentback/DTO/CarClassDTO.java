package com.xml.agentback.DTO;

import com.xml.agentback.model.CarClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarClassDTO {
    private Long id;
    private String carClass;

    public CarClassDTO(){}

    public CarClassDTO(Long id, String carClass) {
        this.id = id;
        this.carClass = carClass;
    }

    public CarClassDTO(CarClass carClass){
        this.id = carClass.getId();
        this.carClass = carClass.getCarClass();
    }

}
