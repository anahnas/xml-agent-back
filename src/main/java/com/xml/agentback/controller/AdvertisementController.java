package com.xml.agentback.controller;

import com.xml.RentACar.wsdl.AdvertisementResponse;
import com.xml.agentback.DTO.AdvertisementDTO;
import com.xml.agentback.model.Advertisement;
import com.xml.agentback.model.Car;
import com.xml.agentback.model.User;
import com.xml.agentback.service.AdvertisementService;
import com.xml.agentback.service.impl.AdvertisementServiceImpl;
import com.xml.agentback.service.impl.CarServiceImpl;
import com.xml.agentback.soap.AdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("advertisement")
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

    @Autowired
    private AdvertisementServiceImpl advertisementService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private AdClient adClient;

    @GetMapping(value="/all", produces = "application/json")
    public ResponseEntity<List<AdvertisementDTO>> getAll() {
        try {
            List<AdvertisementDTO> advertisementDTOS = this.advertisementService.getAll();
            return new ResponseEntity<>(advertisementDTOS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOneAd(@PathVariable("id") Long id)
    {
        AdvertisementDTO advertisementDTO = advertisementService.getOneAd(id);
        if(advertisementDTO != null)
        {
            return new ResponseEntity<>(advertisementDTO,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> newAd(@RequestHeader ("userId") Long userId , @RequestBody AdvertisementDTO advertisementDTO) {

        try {
            System.out.println("Da li je usao u pravljenje auta???");
            /*if(response.getAdvertisementId() == 0){
                return new ResponseEntity<>("Error on posting on main app",HttpStatus.BAD_REQUEST);
            }*/


            Car car = this.advertisementService.newAdvertisement(advertisementDTO, userId);
            System.out.println("Printamo auto: " + car.toString());
            advertisementDTO.getCarDTO().setId(car.getId());
            AdvertisementResponse response = adClient.adResponse(advertisementDTO);

            advertisementDTO.setId(response.getAdvertisementId());
           // advertisementDTO.getCarDTO().setMainId(response.getCarId());
            System.out.println("Saljemo za soap: " + advertisementDTO.toString());

            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value="/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {
        this.advertisementService.uploadImage(image);

        return new ResponseEntity<>(HttpStatus.OK);

    }



}
