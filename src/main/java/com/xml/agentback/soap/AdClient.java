package com.xml.agentback.soap;

import com.xml.RentACar.wsdl.AdvertisementRequest;
import com.xml.RentACar.wsdl.AdvertisementResponse;
import com.xml.RentACar.wsdl.Car;
import com.xml.agentback.DTO.AdvertisementDTO;
import com.xml.agentback.service.AdvertisementService;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class AdClient extends WebServiceGatewaySupport {

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    CarService carService;

    public AdvertisementResponse adResponse(AdvertisementDTO advertisementDTO){

        AdvertisementRequest adRequest = new AdvertisementRequest();
        adRequest.setAdvertiserId(1L);

        Car car = new Car();
        car.setAvailableChildSeats(advertisementDTO.getCarDTO().getAvailableChildSeats());
        car.setCarModelId(advertisementDTO.getCarDTO().getCarModelDTO().getId());
        car.setFuelTypeId(advertisementDTO.getCarDTO().getFuelTypeDTO().getId());
        car.setTransmissionId(advertisementDTO.getCarDTO().getTransmissionDTO().getId());
        car.setKmage(advertisementDTO.getCarDTO().getKmage());
        car.setLimitedKmsPerDay(advertisementDTO.getCarDTO().getLimitKmsPerDay());
        car.setLimitedKms(advertisementDTO.getCarDTO().isLimitedKms());
        car.setWaiver(advertisementDTO.getCarDTO().isWaiver());
        car.setWaiverPricePerDay(advertisementDTO.getCarDTO().getWaiverPricePerDay());
        car.setPricePerDay(advertisementDTO.getCarDTO().getPricePerDay());
        car.setPricePerKm(advertisementDTO.getCarDTO().getPricePerKm());
        // car.setOwnerId(advertisementDTO.getCarDTO().getOwner().getId());

        /*if(advertisementDTO.getCarDTO().getId() != null){
            com.xml.agentback.model.Car oldCar = carService.getOne(advertisementDTO.getCarDTO().getId());
            if(oldCar.getMainId() != null) {
                car.setId(oldCar.getMainId());
            }else{
                car.setId(0L);
            }

        }else{
            car.setId(0L);
        }*/

        adRequest.setCar(car);
        System.out.println("***SALJEMO AUTO IZ AD CLIENTA: " + car.getId() + ", " + car.getCarModelId() + " " + car.getKmage());

        AdvertisementResponse response = (AdvertisementResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/car-service-schema", adRequest,
                        new SoapActionCallback("http://localhost:8081/ws/car-service-schema/advertisementRequest"));

        return response;

    }

}
