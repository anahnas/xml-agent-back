package com.xml.agentback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.agentback.DTO.CarClassDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class CarClass {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String carClass;
    @JsonIgnore
    @OneToMany
    private List<CarModel> carModels = new ArrayList<>();

    public CarClass() {
    }

    public CarClass(String carClass) {
        this.carClass = carClass;
    }

    public CarClass(CarClassDTO carClassDTO) {
        if(carClassDTO.getId() != null)
            this.id = carClassDTO.getId();
        this.carClass = carClassDTO.getCarClass();
    }

}
