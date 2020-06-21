package com.xml.agentback.DTO;

import com.xml.agentback.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CarDTO {
    private Long id;
    private CarModelDTO carModelDTO;
    private FuelTypeDTO fuelTypeDTO;
    private TransmissionDTO transmissionDTO;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean limitedKms;
    private Double limitKmsPerDay;
    private Double kmage;
    private boolean waiver;
    private Integer availableChildSeats;
    private Set<CarRatingDTO> carRatingDTOs = new HashSet<>();
    private UserDTO owner;
    private Set<PromotionDTO> promotionDTOs = new HashSet<>();
   // private Long mainId;


    public CarDTO() {}

    public CarDTO(Car car){
        this.id = car.getId();
        this.carModelDTO = new CarModelDTO(car.getCarModel());
        this.fuelTypeDTO = new FuelTypeDTO(car.getFuelType());
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

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", carModelDTO=" + carModelDTO +
                ", fuelTypeDTO=" + fuelTypeDTO +
                ", transmissionDTO=" + transmissionDTO +
                ", pricePerDay=" + pricePerDay +
                ", pricePerKm=" + pricePerKm +
                ", limitedKms=" + limitedKms +
                ", limitKmsPerDay=" + limitKmsPerDay +
                ", kmage=" + kmage +
                ", waiver=" + waiver +
                ", availableChildSeats=" + availableChildSeats +
                ", carRatingDTOs=" + carRatingDTOs +
                ", owner=" + owner +
                ", promotionDTOs=" + promotionDTOs +
                //", mainId=" + mainId +
                '}';
    }
}
