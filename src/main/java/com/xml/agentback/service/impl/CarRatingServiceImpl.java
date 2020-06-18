package com.xml.agentback.service.impl;

import com.xml.agentback.model.CarRating;
import com.xml.agentback.model.RatingStatus;
import com.xml.agentback.repository.CarRatingRepository;
import com.xml.agentback.service.CarRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarRatingServiceImpl implements CarRatingService {
    @Autowired
    private CarRatingRepository carRatingRepository;

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
        carRating.setRatingStatus(RatingStatus.PENDING);
        return this.carRatingRepository.save(carRating);
    }

    @Override
    public void deleteById(Long id) {
        this.carRatingRepository.deleteById(id);
    }
}
