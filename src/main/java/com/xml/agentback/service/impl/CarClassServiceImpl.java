package com.xml.agentback.service.impl;


import com.xml.agentback.model.CarClass;
import com.xml.agentback.repository.CarClassRepository;
import com.xml.agentback.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassRepository carClassRepository;

    @Override
    public List<CarClass> getAll() {
        return this.carClassRepository.findAll();
    }

    @Override
    public Optional<CarClass> getOneCarClass(Long id) {
        return this.carClassRepository.findById(id);
    }

    @Override
    public CarClass addOne(CarClass carClass) {
        return this.carClassRepository.save(carClass);
    }

    @Override
    public void deleteById(Long id) {
        this.carClassRepository.deleteById(id);

    }
}
