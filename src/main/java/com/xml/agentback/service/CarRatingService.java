package com.xml.agentback.service;

import com.xml.agentback.model.CarRating;

import java.util.List;
import java.util.Optional;

public interface CarRatingService {
    List<CarRating> getAll(Long carId);

    List<CarRating> getAllByUser(Long carId);

    Optional<CarRating> getOne(Long id);

    CarRating addOne(CarRating carRating);

    void deleteById(Long id);

    Double calculate(List<CarRating> carRatings);

}
