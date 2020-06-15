package com.xml.agentback.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.agentback.model.CarModel;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class CarBrandDTO {
    private String name;
    private List<CarModelDTO> carModelDTOs;

    public CarBrandDTO(){}

    public CarBrandDTO(String name, List<CarModelDTO> carModelDTOs) {
        this.name = name;
        this.carModelDTOs = carModelDTOs;
    }
}
