package com.xml.agentback.service;

import com.xml.agentback.model.CarBrand;
import com.xml.agentback.repository.CarBrandRepository;

import java.util.List;
import java.util.Optional;

public interface CarBrandService {

    List<CarBrand> getAll();

    Optional<CarBrand> getOneCarBrand(Long id);

    CarBrand addOne(CarBrand carBrand);

    void deleteById(Long id);
}
