package com.xml.agentback.service.impl;

import com.xml.agentback.model.CarModel;
import com.xml.agentback.repository.CarModelRepository;
import com.xml.agentback.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public List<CarModel> getAll() {
        return this.carModelRepository.findAll();

    }

    @Override
    public Optional<CarModel> getOneCarModel(Long id) {
        return this.carModelRepository.findById(id);
    }

    @Override
    public CarModel addOne(CarModel carModel) {
        return this.carModelRepository.save(carModel);
    }

    @Override
    public void deleteById(Long id) {
        this.carModelRepository.deleteById(id);
    }
}
