package com.xml.agentback.model;

import com.xml.agentback.DTO.CarDTO;
import com.xml.agentback.DTO.CarRatingDTO;
import com.xml.agentback.DTO.PromotionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private CarModel carModel;
    private FuelType fuelType;
    private Transmission transmission;
    private CarClass carClass;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean limitedKms;
    private Double limitKmsPerDay;
    private Double kmage;
    private boolean waiver;
    private Integer availableChildSeats;
    @ElementCollection(targetClass=Long.class)
    private Set<CarRating> carRatings;
    private User owner;
    //private List<Image> images;
    @ElementCollection(targetClass=Long.class)
    private Set<Promotion> promotions;

    public Car() { }

    public Car(CarModel carModel, FuelType fuelType, Transmission transmission, CarClass carClass, Double pricePerDay, Double pricePerKm, boolean limitedKms, Double limitKmsPerDay, Double kmage, boolean waiver, Integer availableChildSeats, Set<CarRating> carRatings, User owner, Set<Promotion> promotions) {
        this.carModel = carModel;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.carClass = carClass;
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
        this.limitedKms = limitedKms;
        this.limitKmsPerDay = limitKmsPerDay;
        this.kmage = kmage;
        this.waiver = waiver;
        this.availableChildSeats = availableChildSeats;
        this.carRatings = carRatings;
        this.owner = owner;
        this.promotions = promotions;
    }

    public Car(CarDTO carDTO){
        this.carModel = new CarModel(carDTO.getCarModelDTO());
        this.fuelType = new FuelType(carDTO.getFuelTypeDTO());
        this.transmission = new Transmission(carDTO.getTransmissionDTO());
        this.carClass = new CarClass(carDTO.getCarClassDTO());
        this.pricePerDay = carDTO.getPricePerDay();
        this.pricePerKm = carDTO.getPricePerKm();
        this.limitedKms = carDTO.isLimitedKms();
        this.limitKmsPerDay = carDTO.getLimitKmsPerDay();
        this.kmage = carDTO.getKmage();
        this.waiver = carDTO.isWaiver();
        this.availableChildSeats = carDTO.getAvailableChildSeats();
        for(CarRatingDTO carRatingDTO : carDTO.getCarRatingDTOs()){
            this.carRatings.add(new CarRating(carRatingDTO));
        }
        this.owner = new User(carDTO.getOwner());
        for(PromotionDTO promotionDTO : carDTO.getPromotionDTOs()){
            this.promotions.add(new Promotion(promotionDTO));
        }
    }
}
