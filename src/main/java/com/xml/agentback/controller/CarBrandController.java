package com.xml.agentback.controller;

import com.xml.agentback.model.CarBrand;
import com.xml.agentback.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carBrand")
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    @GetMapping(value = "/carBrand/getAll")
    public ResponseEntity<List<CarBrand>> getAll() {
        try {
            List<CarBrand> fuelTypes = this.carBrandService.getAll();
            return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity getCarBrand(@PathVariable Long id) {
        Optional<CarBrand> retVal = carBrandService.getOneCarBrand(id);
        if(retVal.get() != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<?> addCarBrand(@RequestBody CarBrand carBrand){
        CarBrand retVal = this.carBrandService.addOne(carBrand);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCarBrand(@PathVariable Long id) {

        try {
            Optional<CarBrand> carBrand = this.carBrandService.getOneCarBrand(id);
            if (carBrand.get() != null) {
                this.carBrandService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
