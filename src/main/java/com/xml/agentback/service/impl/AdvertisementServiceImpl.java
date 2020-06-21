package com.xml.agentback.service.impl;

import com.xml.agentback.DTO.*;
import com.xml.agentback.model.*;
import com.xml.agentback.repository.*;
import com.xml.agentback.service.AdvertisementService;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServletContext servletContext;

    @Override
    public ArrayList<AdvertisementDTO> getAll() {
            ArrayList<AdvertisementDTO> advertisementDTOS = new ArrayList<>();
            List<Advertisement> ads = advertisementRepository.findAll();

            for(Advertisement ad : ads)
            {
                advertisementDTOS.add(ent2DTO(ad));
            }

        return advertisementDTOS;
    }

    @Override
    public void add(Advertisement advertisement) {
        this.advertisementRepository.save(advertisement);
    }


    @Override
    public Advertisement save(Advertisement advertisement) {
        return this.advertisementRepository.save(advertisement);
    }

    @Override
    public Long newAdvertisement(AdvertisementDTO advertisementDTO, Long userId) {
        Car car = new Car();
        Advertisement newAd = new Advertisement();

        User user = this.userRepository.getOne(userId);
        advertisementDTO.setUserDTO(new UserDTO(user));
        advertisementDTO.getCarDTO().setOwner(new UserDTO(user));

        if(advertisementDTO.getCarDTO().getId() == null) {
            System.out.println("making a new car...");
            car = carService.addOne(new Car(advertisementDTO.getCarDTO()));
        } else {

            car.setId(advertisementDTO.getCarDTO().getId());
            car.getOwner().setId(advertisementDTO.getCarDTO().getOwner().getId());
            car = carService.addOne(new Car(advertisementDTO.getCarDTO()));

            System.out.println("Upao nam je ovje mozda");
            //car = carService.getOne(advertisementDTO.getCarDTO().getId());

            /*if(car.getId() == null)
                car.setId(advertisementDTO.getCarDTO().getId());

            carService.addOne(car);*/
        }

        newAd.setId(advertisementDTO.getId());
        newAd.setCar(car);
        newAd.setEndDate(advertisementDTO.getEndDate());
        newAd.setStartDate(advertisementDTO.getStartDate());


        Advertisement ADded = save(newAd);

        return car.getId();
    }

    @Override
    public void uploadImage(MultipartFile image) throws IOException {
        String pathStr= servletContext.getRealPath(image.getOriginalFilename());
        System.out.println(pathStr);
        byte[] bytes = image.getBytes();
        Files.write(Paths.get(pathStr), bytes);
        this.carService.setImagePath(pathStr, image.getOriginalFilename());
    }

    @Override
    public AdvertisementDTO getOneAd(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElse(null);
        if(advertisement != null)
        {
            return ent2DTO(advertisement);
        }
        else return null;
    }

    @Override
    public List<Advertisement> findAdvertisersAds(Long advertiserId) {
        return null;
    }


    public AdvertisementDTO ent2DTO(Advertisement advertisement)
    {
        AdvertisementDTO newAdDTO = new AdvertisementDTO();
        Car car = advertisement.getCar();
        CarDTO newCar = new CarDTO();
        CarModel carModel = car.getCarModel();
        CarModelDTO carModelDTO = new CarModelDTO();
        CarBrand carBrand = carModel.getCarBrand();
        CarBrandDTO carBrandDTO = new CarBrandDTO();
        CarClassDTO carClassDTO = new CarClassDTO();
        FuelType fuelType = car.getFuelType();
        FuelTypeDTO fuelTypeDTO = new FuelTypeDTO();
        Transmission transmission = car.getTransmission();
        TransmissionDTO transmissionDTO = new TransmissionDTO();


        newAdDTO.setId(advertisement.getId());
        newAdDTO.setStartDate(advertisement.getStartDate());
        newAdDTO.setEndDate(advertisement.getEndDate());


        newCar.setId(car.getId());
        newCar.setAvailableChildSeats(car.getAvailableChildSeats());
        newCar.setKmage(car.getKmage());
        newCar.setLimitedKms(car.isLimitedKms());
        newCar.setLimitKmsPerDay(car.getLimitKmsPerDay());

        carBrandDTO.setId(carBrand.getId());
        carBrandDTO.setName(carBrand.getName());
        carModelDTO.setId(carModel.getId());
        carModelDTO.setName(carModel.getName());
        fuelTypeDTO.setId(fuelType.getId());
        fuelTypeDTO.setType(fuelType.getType());
        transmissionDTO.setId(transmission.getId());
        transmissionDTO.setType(transmission.getType());


        newCar.setCarModelDTO(carModelDTO);
        newCar.setFuelTypeDTO(fuelTypeDTO);
        newCar.setTransmissionDTO(transmissionDTO);


        newAdDTO.setCarDTO(newCar);
        return newAdDTO;
    }


}
