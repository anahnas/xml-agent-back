package com.xml.agentback.DTO;

import com.xml.agentback.model.CarBrand;
import com.xml.agentback.model.CarClass;
import com.xml.agentback.model.CarModel;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CarModelDTO {
    private Long id;
    private String name;
    private CarBrandDTO carBrandDTO;
    private CarClassDTO carClassDTO;

    public CarModelDTO(){}

    public CarModelDTO(String name, CarBrandDTO carBrandDTO, CarClassDTO carClassDTO) {
        this.name = name;
        this.carBrandDTO = carBrandDTO;
        this.carClassDTO = carClassDTO;
    }

    public CarModelDTO(CarModel carModel) {
        this.id = carModel.getId();
        this.name = carModel.getName();
        this.carBrandDTO  = new CarBrandDTO(carModel.getCarBrand());
        this.carClassDTO = new CarClassDTO(carModel.getCarClass());
    }
}
