package com.xml.agentback.service.impl;

import com.xml.agentback.model.Transmission;
import com.xml.agentback.repository.TransmissionRepository;
import com.xml.agentback.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionServiceImpl implements TransmissionService {

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Override
    public List<Transmission> getAll() {
        return this.transmissionRepository.findAll();
    }

    @Override
    public Optional<Transmission> getTransmission(Long id) {
        return this.transmissionRepository.findById(id);
    }

    @Override
    public Transmission addTransmission(Transmission transmission) {
        return this.transmissionRepository.save(transmission);
    }

    @Override
    public void deleteById(Long id) {

        this.transmissionRepository.deleteById(id);

    }

}
