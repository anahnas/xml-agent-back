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
    public Car addCar(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car getCar(Long id) {
        if(this.carRepository.findById(id).isPresent())
            return this.carRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public Car editCar(Car carDTO) {
            Car car = this.carRepository.getOne(carDTO.getId());

            car.setCarModelId(car.getCarModelId());
            car.setFuelTypeId(carDTO.getFuelTypeId());
            car.setTransmissionId(carDTO.getTransmissionId());
            car.setCarClassId(carDTO.getCarClassId());
            car.setPricePerDay(carDTO.getPricePerDay());
            car.setPricePerKm(carDTO.getPricePerKm());
            car.setLimitedKms(carDTO.isLimitedKms());
            car.setLimitKmsPerDay(carDTO.getLimitKmsPerDay());
            car.setKmage(carDTO.getKmage());
            car.setWaiver(carDTO.isWaiver());
            car.setAvailableChildSeats(carDTO.getAvailableChildSeats());
            car.setOwnerId(carDTO.getOwnerId());
            car.setCarRatingIds(carDTO.getCarRatingIds());
            car.setPromotionIds(carDTO.getPromotionIds());

            return this.carRepository.save(car);
        }

    @Override
    public boolean removeCar(Long id) {
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
            return this.carRepository.findByCarModelIdAndAndCarClassIdAndAndFuelTypeIdAndAndTransmissionId(car.getCarModelId(), car.getCarClassId(), car.getFuelTypeId(), car.getTransmissionId());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
