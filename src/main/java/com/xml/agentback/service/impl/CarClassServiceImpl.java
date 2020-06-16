package com.xml.agentback.service.impl;


import com.xml.agentback.model.CarClass;
import com.xml.agentback.repository.CarClassRepository;
import com.xml.agentback.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassRepository carClassRepository;

    @Override
    public List<CarClass> getAll() {
        return this.carClassRepository.findAll();
    }

    @Override
    public CarClass getOne(Long id) {
        return this.carClassRepository.findById(id).orElse(null);
    }

    @Override
    public CarClass addOne(CarClass carClass) {
        return this.carClassRepository.save(carClass);
    }

    @Override
    public CarClass update(CarClass carClass) {
        CarClass toUpdate = this.carClassRepository.getOne(carClass.getId());
        toUpdate.setCarClass(carClass.getCarClass());
        return this.carClassRepository.save(toUpdate);
    }

    @Override
    public void deleteById(Long id) {
        this.carClassRepository.deleteById(id);

    }
}
