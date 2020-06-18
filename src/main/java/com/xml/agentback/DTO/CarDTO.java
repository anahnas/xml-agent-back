package com.xml.agentback.DTO;

import com.xml.agentback.model.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class CarDTO {
    private Long id;
    private CarModelDTO carModelDTO;
    private CarBrandDTO carBrandDTO;
    private FuelTypeDTO fuelTypeDTO;
    private TransmissionDTO transmissionDTO;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean limitedKms;
    private Double limitKmsPerDay;
    private Double kmage;
    private boolean waiver;
    private Integer availableChildSeats;
    private Set<CarRatingDTO> carRatingDTOs;
    private UserDTO owner;
    private Set<PromotionDTO> promotionDTOs;

    public CarDTO() {}

    public CarDTO(Car car){
        this.id = car.getId();
        this.carModelDTO = new CarModelDTO(car.getCarModel());
        this.fuelTypeDTO = new FuelTypeDTO(car.getFuelType());
        this.carBrandDTO = new CarBrandDTO(car.getCarBrand());
        this.transmissionDTO = new TransmissionDTO(car.getTransmission());
        this.pricePerDay = car.getPricePerDay();
        this.pricePerKm = car.getPricePerKm();
        this.limitedKms = car.isLimitedKms();
        this.limitKmsPerDay = car.getLimitKmsPerDay();
        this.kmage = car.getKmage();
        this.waiver = car.isWaiver();
        this.availableChildSeats = car.getAvailableChildSeats();
        for(CarRating carRating : car.getCarRatings()){
            this.carRatingDTOs.add(new CarRatingDTO(carRating));
        }
        this.owner = new UserDTO(car.getOwner());
        for(Promotion promotion : car.getPromotions()){
            this.promotionDTOs.add(new PromotionDTO(promotion));
        }
    }

}
