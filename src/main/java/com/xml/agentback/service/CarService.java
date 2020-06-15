package com.xml.agentback.service;

import com.xml.agentback.model.Car;
import com.xml.agentback.model.Rental;

import java.util.List;

public interface CarService {

    Car addCar(Car car);

    List<Car> getAll();

    Car getCar(Long id);

    // Car editCar(Car car);

    boolean removeCar(Long id);

    Rental blockCar(Rental rental);

}
