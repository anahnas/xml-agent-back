package com.xml.agentback.controller;


import com.xml.agentback.model.RentRequest;
import com.xml.agentback.service.CarService;
import com.xml.agentback.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentRequestController {

    @Autowired
    private RentRequestService rentService;

    @Autowired
    private CarService carService; // preko ovog servisa isto pravimo oglas

    @GetMapping(value = "/rent/getAll")
    public ResponseEntity<List<RentRequest>> getAll() {

        try {
            List<RentRequest> rentRequests = this.rentService.getAll();

            return new ResponseEntity<>(rentRequests, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/rent/getPaid")
    public ResponseEntity<List<RentRequest>> getPaid() {

        try {
            List<RentRequest> rentRequests = this.rentService.getPaidRentReqs();

            return new ResponseEntity<>(rentRequests, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/rent/getPending")
    public ResponseEntity<List<RentRequest>> getPending() {

        try {
            List<RentRequest> rentRequests = this.rentService.getPendingReqs();

            return new ResponseEntity<>(rentRequests, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "/rent/getCancellable")
    public ResponseEntity<List<RentRequest>> getCancellable() {

        try {
            List<RentRequest> rentRequests = this.rentService.getCancelableRR();

            return new ResponseEntity<>(rentRequests, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/rent/create")
    public ResponseEntity<?> createRentRequest(@RequestBody RentRequest rentRequest) {
        try {
            this.rentService.createRentReq(rentRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}
