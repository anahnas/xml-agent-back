package com.xml.agentback.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuelTypeDTO {
    private String type;

    public FuelTypeDTO(){}

    public FuelTypeDTO(String type) {
        this.type = type;
    }
}
