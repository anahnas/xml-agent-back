package com.xml.agentback.controller;


import com.xml.agentback.model.CarClass;
import com.xml.agentback.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarClassController {

    @Autowired
    private CarClassService carClassService;

    @GetMapping(value = "/carClass/getAll")
    public ResponseEntity<List<CarClass>> getAll() {
        try {
            List<CarClass> fuelTypes = this.carClassService.getAll();
            return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/carClass/getOne/{id}")
    public ResponseEntity<?> getOneCarClass(@PathVariable("id") Long id){
        Optional<CarClass> retVal = carClassService.getOneCarClass(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/carClass/deleteOne/{id}")
    public ResponseEntity deleteCarClass(@PathVariable("id") Long id) {

        try {
            Optional<CarClass> carClass = this.carClassService.getOneCarClass(id);
            if (carClass != null) {
                this.carClassService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping(value="/carClass/add")
    public ResponseEntity<?> addCarBrand(@RequestBody CarClass carClass){
        CarClass retVal = this.carClassService.addOne(carClass);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
