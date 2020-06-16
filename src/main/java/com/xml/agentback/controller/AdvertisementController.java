package com.xml.agentback.controller;

import com.xml.agentback.DTO.AdvertisementDTO;
import com.xml.agentback.model.Advertisement;
import com.xml.agentback.service.AdvertisementService;
import com.xml.agentback.service.impl.AdvertisementServiceImpl;
import com.xml.agentback.service.impl.CarServiceImpl;
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
@RequestMapping("advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementServiceImpl advertisementService;

    @Autowired
    private CarServiceImpl carService;

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





}
