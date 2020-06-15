package com.xml.agentback.service;

import com.xml.agentback.model.CarClass;

import java.util.List;
import java.util.Optional;

public interface CarClassService {

    List<CarClass> getAll();

    Optional<CarClass> getOne(Long id);

    CarClass addOne(CarClass carClass);

    CarClass update(CarClass carClass);

    void deleteById(Long id);
}
