package com.xml.agentback.DTO;

import com.xml.agentback.model.FuelType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuelTypeDTO {
    private Long id;
    private String type;

    public FuelTypeDTO(){}

    public FuelTypeDTO(String type) {
        this.type = type;
    }

    public FuelTypeDTO(FuelType fuelType) {
        this.id = fuelType.getId();
        this.type = fuelType.getType();
    }
}
