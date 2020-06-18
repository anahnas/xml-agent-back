package com.xml.agentback.controller;

import com.xml.agentback.DTO.CarRatingDTO;
import com.xml.agentback.model.CarRating;
import com.xml.agentback.service.CarRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private CarRatingService carRatingService;

    @GetMapping(value="/{id}/rating")
    public ResponseEntity<?> getAllByUser(@PathVariable("id") Long userId){
        try {
            List<CarRating> carRatings = this.carRatingService.getAllByUser(userId);
            List<CarRatingDTO> CarRatingDTOs = new ArrayList<>();
            for(CarRating carRating : carRatings){
                CarRatingDTOs.add(new CarRatingDTO(carRating));
            }
            return new ResponseEntity<>(CarRatingDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
