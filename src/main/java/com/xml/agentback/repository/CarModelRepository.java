package com.xml.agentback.repository;

import com.xml.agentback.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {


    @Override
    List<CarModel> findAll();
}
