package com.xml.agentback.controller;

import com.xml.agentback.DTO.CarBrandDTO;
import com.xml.agentback.DTO.CarClassDTO;
import com.xml.agentback.model.CarBrand;
import com.xml.agentback.model.CarClass;
import com.xml.agentback.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carBrand")
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    @GetMapping
    public ResponseEntity<List<CarBrandDTO>> getAll() {
        try {
            List<CarBrand> carBrands = this.carBrandService.getAll();
            List<CarBrandDTO> carBrandDTOs = new ArrayList<>();
            for(CarBrand carBrand : carBrands){
                carBrandDTOs.add(new CarBrandDTO(carBrand));
            }
            return new ResponseEntity<>(carBrandDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity getCarBrand(@PathVariable Long id) {
        Optional<CarBrand> carBrand = Optional.ofNullable(carBrandService.getOne(id));
        if(carBrand != null){
            CarBrandDTO retVal = new CarBrandDTO(carBrand.get());
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<?> addCarBrand(@RequestBody CarBrandDTO carBrandDTO){
        CarBrand carBrand = new CarBrand(carBrandDTO);
        carBrand = this.carBrandService.addOne(carBrand);
        if(carBrand != null)
            return new ResponseEntity<>(carBrand, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCarBrand(@PathVariable Long id) {

        try {
            Optional<CarBrand> carBrand = Optional.ofNullable(this.carBrandService.getOne(id));
            if (carBrand != null) {
                this.carBrandService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCarBrand(@RequestBody CarBrandDTO carBrandDTO){
        CarBrand retVal = this.carBrandService.update(new CarBrand(carBrandDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
