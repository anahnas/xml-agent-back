package com.xml.agentback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne
    private CarModel carModel;
    @ManyToOne
    private FuelType fuelType;
    @ManyToOne
    private Transmission transmission;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean limitedKms;
    private Double limitKmsPerDay;
    private Double kmage;
    private boolean waiver;
    private Integer availableChildSeats;
    @OneToMany
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<CarRating> carRatings = new HashSet<>();
    @ManyToOne
    private User owner;
    //private List<Image> images;
    @OneToMany
    private Set<Promotion> promotions = new HashSet<>();

    public Car() { }

    public Car(Long id) {
        this.id = id;
    }

    public Car(CarModel carModel, FuelType fuelType, Transmission transmission, Double pricePerDay, Double pricePerKm, boolean limitedKms, Double limitKmsPerDay, Double kmage, boolean waiver, Integer availableChildSeats, Set<CarRating> carRatings, User owner, Set<Promotion> promotions) {
        this.carModel = carModel;
        this.fuelType = fuelType;
        this.transmission = transmission;
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
        if(carDTO.getId() != null)
            this.id = carDTO.getId();
        this.carModel = new CarModel(carDTO.getCarModelDTO());
        this.fuelType = new FuelType(carDTO.getFuelTypeDTO());
        this.transmission = new Transmission(carDTO.getTransmissionDTO());
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
