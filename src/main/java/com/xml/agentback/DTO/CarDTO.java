package com.xml.agentback.DTO;

import com.xml.agentback.model.CarRating;
import com.xml.agentback.model.FuelType;
import com.xml.agentback.model.Transmission;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class CarDTO {
    private CarModelDTO carModelDTO;
    private FuelTypeDTO fuelTypeDTO;
    private TransmissionDTO transmissionDTO;
    private CarClassDTO carClassDTO;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean limitedKms;
    private Double limitKmsPerDay;
    private Double kmage;
    private boolean waiver;
    private Integer availableChildSeats;
    private Set<CarRatingDTO> carRatings;
    private UserDTO owner;
    private Set<PromotionDTO> promotionDTOs;

}
