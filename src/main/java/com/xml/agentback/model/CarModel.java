package com.xml.agentback.model;


import com.xml.agentback.DTO.CarBrandDTO;
import com.xml.agentback.DTO.CarModelDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class CarModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    private CarBrand carBrand;
    @ManyToOne
    private CarClass carClass;

    public CarModel() {
    }

    public CarModel(String name, CarBrand carBrand, CarClass carClass) {
        this.name = name;
        this.carBrand = carBrand;
        this.carClass = carClass;
    }

    public CarModel(CarModelDTO carModelDTO) {
        if(carModelDTO.getId() != null)
            this.id = carModelDTO.getId();
        this.name = carModelDTO.getName();
        this.carBrand = new CarBrand(carModelDTO.getCarBrandDTO());
        this.carClass = new CarClass(carModelDTO.getCarClassDTO());
    }
}
