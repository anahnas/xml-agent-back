package com.xml.agentback.DTO;

import com.xml.agentback.model.CarBrand;
import com.xml.agentback.model.CarClass;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CarModelDTO {
    private String name;
    private CarBrandDTO carBrandDTO;
    private CarClassDTO carClassDTO;

    public CarModelDTO(){}

    public CarModelDTO(String name, CarBrandDTO carBrandDTO, CarClassDTO carClassDTO) {
        this.name = name;
        this.carBrandDTO = carBrandDTO;
        this.carClassDTO = carClassDTO;
    }
}
