package com.xml.agentback.controller;


import com.xml.agentback.model.Car;
import com.xml.agentback.model.Rental;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value="/cars/all")
    public ResponseEntity<?> getAllCars(){
        ArrayList<Car> retVal = (ArrayList<Car>) carService.getAll();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/cars/{id}")
    public ResponseEntity<?> getCar(@PathVariable("id") Long id){
        Car retVal = carService.getCar(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/notAvailable")
    public ResponseEntity<?> notAvailable(@RequestBody Rental rental) {
        Rental r = this.carService.blockCar(rental);
        if( r == null )
            return new ResponseEntity<>("Car blocking error!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>( "Car is not available any more.", HttpStatus.OK);
    }


}
