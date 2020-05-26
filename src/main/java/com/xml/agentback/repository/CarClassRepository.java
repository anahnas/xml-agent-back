package com.xml.agentback.repository;

import com.xml.agentback.model.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {

    @Override
    Optional<CarClass> findById(Long aLong);

    @Override
    List<CarClass> findAll();
}
