package com.xml.agentback.service;

import com.xml.agentback.model.Transmission;

import java.util.List;
import java.util.Optional;

public interface TransmissionService {

    List<Transmission> getAll();

    Optional<Transmission> getTransmission(Long id);

    Transmission addTransmission(Transmission transmission);

    void deleteById(Long id);

}
