package com.xml.agentback.service;

import com.xml.agentback.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelService {

    List<CarModel> getAll();

    Optional<CarModel> getOneCarModel(Long id);

    CarModel addOne(CarModel carModel);

    void deleteById(Long id);

}
