package com.xml.agentback.service.impl;

import com.xml.agentback.model.CarModel;
import com.xml.agentback.repository.CarModelRepository;
import com.xml.agentback.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public List<CarModel> getAll() {
        return this.carModelRepository.findAll();

    }

    @Override
    public CarModel getOne(Long id) {
        return this.carModelRepository.findById(id).orElse(null);
    }

    @Override
    public CarModel addOne(CarModel carModel) {
        return this.carModelRepository.save(carModel);
    }

    @Override
    public CarModel update(CarModel carModel) {
        CarModel toUpdate = this.carModelRepository.getOne(carModel.getId());
        toUpdate.setCarBrand(carModel.getCarBrand());
        toUpdate.setCarClass(carModel.getCarClass());
        toUpdate.setName(carModel.getName());
        return this.carModelRepository.save(carModel);
    }

    @Override
    public void deleteById(Long id) {
        this.carModelRepository.deleteById(id);
    }

}
