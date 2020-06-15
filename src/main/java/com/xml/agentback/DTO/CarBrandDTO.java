package com.xml.agentback.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.agentback.model.CarBrand;
import com.xml.agentback.model.CarModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CarBrandDTO {
    private Long id;
    private String name;
    private List<CarModelDTO> carModelDTOs = new ArrayList<>();

    public CarBrandDTO(){}

    public CarBrandDTO(String name, List<CarModelDTO> carModelDTOs) {
        this.name = name;
        this.carModelDTOs = carModelDTOs;
    }

    public CarBrandDTO (CarBrand carBrand){
        this.id = carBrand.getId();
        this.name = carBrand.getName();
        for(CarModel carModel:carBrand.getCarModels()){
            carModelDTOs.add(new CarModelDTO(carModel));
        }
    }
}
