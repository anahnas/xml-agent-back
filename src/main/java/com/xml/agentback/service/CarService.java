package com.xml.agentback.service;

import com.xml.agentback.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getAll();

    Car getOne(Long id);

    Car addOne(Car car);

    Car update(Car car);

    boolean deleteById(Long id);

    List<Car> searchCars(Car car);

}
