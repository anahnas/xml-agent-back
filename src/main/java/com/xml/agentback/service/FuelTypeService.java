package com.xml.agentback.service;

import com.xml.agentback.model.FuelType;

import java.util.List;
import java.util.Optional;

public interface FuelTypeService {

    List<FuelType> getAll();

    Optional<FuelType> getFueltType(Long id);

    FuelType addFuelType(FuelType fuelType);

    void deleteById(Long id);


}