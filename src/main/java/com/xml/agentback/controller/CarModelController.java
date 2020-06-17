package com.xml.agentback.controller;

import com.xml.agentback.DTO.CarBrandDTO;
import com.xml.agentback.DTO.CarModelDTO;
import com.xml.agentback.model.CarBrand;
import com.xml.agentback.model.CarModel;
import com.xml.agentback.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carModel")
@CrossOrigin("http://localhost:4200")
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @GetMapping
    public ResponseEntity<List<CarModel>> getAll() {
        try {
            List<CarModel> fuelTypes = this.carModelService.getAll();
            return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOneCarModel(@PathVariable("id") Long id){
        CarModel retVal = carModelService.getOne(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCarModel(@PathVariable("id") Long id) {

        try {
            CarModel carModel = this.carModelService.getOne(id);
            if (carModel != null) {
                this.carModelService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCarModel(@RequestBody CarModel carModel){
        CarModel retVal = this.carModelService.addOne(carModel);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> updateCarModel(@RequestBody CarModelDTO carModelDTO){
        CarModel retVal = this.carModelService.update(new CarModel(carModelDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
