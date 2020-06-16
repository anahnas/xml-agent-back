package com.xml.agentback.service;

import com.xml.agentback.model.Transmission;

import java.util.List;
import java.util.Optional;

public interface TransmissionService {

    List<Transmission> getAll();

    Transmission getOne(Long id);

    Transmission addOne(Transmission transmission);

    Transmission update(Transmission transmission);

    void deleteById(Long id);

}
