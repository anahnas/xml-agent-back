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
    public Transmission getOne(Long id) {
        return this.transmissionRepository.findById(id).orElse(null);
    }

    @Override
    public Transmission addOne(Transmission transmission) {
        return this.transmissionRepository.save(transmission);
    }

    @Override
    public Transmission update(Transmission transmission){
        Transmission toUpdate = this.transmissionRepository.getOne(transmission.getId());
        toUpdate.setType(transmission.getType());
        return this.transmissionRepository.save(toUpdate);
    }

    @Override
    public void deleteById(Long id) {

        this.transmissionRepository.deleteById(id);

    }

}
