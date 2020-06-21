package com.xml.agentback.service.impl;

import com.xml.agentback.model.Car;
import com.xml.agentback.model.CarRating;
import com.xml.agentback.model.RatingStatus;
import com.xml.agentback.model.User;
import com.xml.agentback.repository.CarRatingRepository;
import com.xml.agentback.repository.CarRepository;
import com.xml.agentback.repository.UserRepository;
import com.xml.agentback.service.CarRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarRatingServiceImpl implements CarRatingService {
    @Autowired
    private CarRatingRepository carRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarRating> getAll(Long carId) {
        return this.carRatingRepository.findAllByCarId(carId);
    }

    @Override
    public List<CarRating> getAllByUser(Long userId) {
        return this.carRatingRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<CarRating> getOne(Long id) {
        return this.carRatingRepository.findById(id);
    }

    @Override
    public CarRating addOne(CarRating carRating) {
        try{
            carRating.setRatingStatus(RatingStatus.PENDING);
            User user = this.userRepository.findByUsername(carRating.getUser().getUsername());
            carRating.setUser(user);
            if(user == null)
                return null;
            Car car = this.carRepository.getOne(carRating.getCar().getId());
            carRating.setCar(car);

            carRating = this.carRatingRepository.save(carRating);
            return this.carRatingRepository.getOne(carRating.getId());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteById(Long id) {
        this.carRatingRepository.deleteById(id);
    }

    @Override
    public Double calculate(List<CarRating> carRatings) {
        Double retVal = 0.0;
        for(CarRating carRating : carRatings){
            retVal += carRating.getRating();
        }
        return retVal / carRatings.size();
    }
}
