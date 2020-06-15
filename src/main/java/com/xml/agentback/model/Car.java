package com.xml.agentback.model;

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

    public Car() { }
}
