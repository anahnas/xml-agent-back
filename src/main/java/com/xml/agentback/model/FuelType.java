package com.xml.agentback.model;

import com.xml.agentback.DTO.FuelTypeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class FuelType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;

    public FuelType() {
    }

    public FuelType(String type) {
        this.type = type;
    }

    public FuelType(FuelTypeDTO fuelTypeDTO) {
        if(fuelTypeDTO.getId() != null)
            this.id = fuelTypeDTO.getId();
        this.type = fuelTypeDTO.getType();
    }

}
