package com.xml.agentback.repository;

import com.xml.agentback.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {


    @Override
    List<CarBrand> findAll();
}
