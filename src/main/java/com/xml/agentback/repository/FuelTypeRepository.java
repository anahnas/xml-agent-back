package com.xml.agentback.repository;

import com.xml.agentback.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {


    @Override
    List<FuelType> findAll();

    void deleteById(Long id);
    FuelType save(FuelType fuelType);
}

