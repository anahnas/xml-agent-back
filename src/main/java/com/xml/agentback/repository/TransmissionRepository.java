package com.xml.agentback.repository;

import com.xml.agentback.model.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {


    @Override
    List<Transmission> findAll();

    @Override
    void deleteById(Long aLong);

    Transmission save(Transmission transmission);

}
