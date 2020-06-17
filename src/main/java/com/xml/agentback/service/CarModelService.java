package com.xml.agentback.service;

import com.xml.agentback.model.CarModel;

import java.util.List;

public interface CarModelService {

    List<CarModel> getAll();

    CarModel getOne(Long id);

    CarModel addOne(CarModel carModel);

    CarModel update(CarModel carModel);

    void deleteById(Long id);

}
