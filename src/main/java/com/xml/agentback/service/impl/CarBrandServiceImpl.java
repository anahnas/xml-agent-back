package com.xml.agentback.service.impl;

import com.xml.agentback.model.CarBrand;
import com.xml.agentback.repository.CarBrandRepository;
import com.xml.agentback.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public List<CarBrand> getAll() {
        return this.carBrandRepository.findAll();
    }

    @Override
    public Optional<CarBrand> getOneCarBrand(Long id) {
        return this.carBrandRepository.findById(id);
    }

    @Override
    public CarBrand addOne(CarBrand carBrand) {
        return this.carBrandRepository.save(carBrand);
    }

    @Override
    public void deleteById(Long id) {
        this.carBrandRepository.deleteById(id);


    }
}
