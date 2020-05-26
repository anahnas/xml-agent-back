package com.xml.agentback.service.impl;


import com.xml.agentback.model.FuelType;
import com.xml.agentback.repository.FuelTypeRepository;
import com.xml.agentback.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;


    @Override
    public List<FuelType> getAll() {
        return this.fuelTypeRepository.findAll();
    }

    @Override
    public Optional<FuelType> getFueltType(Long id) {
        return this.fuelTypeRepository.findById(id);

    }

    @Override
    public FuelType addFuelType(FuelType fuelType) {
        return this.fuelTypeRepository.save(fuelType);
    }

    @Override
    public void deleteById(Long id) {

        this.fuelTypeRepository.deleteById(id);
    }

}
