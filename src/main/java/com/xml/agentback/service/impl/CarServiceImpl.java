package com.xml.agentback.service.impl;

import com.xml.agentback.model.Car;
import com.xml.agentback.repository.CarRepository;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car addOne(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car getOne(Long id) {
        if(this.carRepository.findById(id).isPresent())
            return this.carRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public Car update(Car car) {
            Car toUpdate = this.carRepository.getOne(car.getId());

            toUpdate.setCarModel(car.getCarModel());
            car.setFuelType(car.getFuelType());
            car.setTransmission(car.getTransmission());
            car.setCarClass(car.getCarClass());
            car.setPricePerDay(car.getPricePerDay());
            car.setPricePerKm(car.getPricePerKm());
            car.setLimitedKms(car.isLimitedKms());
            car.setLimitKmsPerDay(car.getLimitKmsPerDay());
            car.setKmage(car.getKmage());
            car.setWaiver(car.isWaiver());
            car.setAvailableChildSeats(car.getAvailableChildSeats());
            car.setOwner(car.getOwner());
            car.setCarRatings(car.getCarRatings());
            car.setPromotions(car.getPromotions());

            return this.carRepository.save(car);
        }

    @Override
    public boolean deleteById(Long id) {
        try{
            this.carRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Car> searchCars(Car car) {
        try {
            return new ArrayList<>();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
