package com.xml.agentback.model;

import com.xml.agentback.DTO.CarBrandDTO;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class CarBrand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @JsonIgnore
    @OneToMany
    private List<CarModel> carModels;

    public CarBrand() {
    }

    public CarBrand(String name) {
        this.name = name;
    }

    public CarBrand(CarBrandDTO carBrandDTO) {
        this.id = carBrandDTO.getId();
        this.name = carBrandDTO.getName();
    }
}
