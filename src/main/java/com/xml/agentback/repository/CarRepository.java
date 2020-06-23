package com.xml.agentback.repository;

import com.xml.agentback.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findById(Long id);
    ArrayList<Car> findAll();
    void deleteById(Long id);
    Car save(Car car);
    @Query(value = "select c from Car c where c.id = ?1 ")
    List<Car> findAllById(Long id);
    //ArrayList<Car> findByCarModelIdAndAndCarClassIdAndAndFuelTypeIdAndAndTransmissionId(Long carModelId, Long carClassId, Long fuelTypeId, Long transmissionId);
}



