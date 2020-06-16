package com.xml.agentback.service;

import com.xml.agentback.DTO.CarDTO;
import com.xml.agentback.model.Car;
import com.xml.agentback.model.Rental;

import java.util.List;

public interface CarService {

    List<Car> getAll();

    Car getOne(Long id);

    Car newCar(CarDTO carDTO);

    Car addOne(Car car);

    Car update(Car car);

    boolean deleteById(Long id);

    Rental blockCar(Rental rental);

}
