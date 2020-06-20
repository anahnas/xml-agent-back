package com.xml.agentback.controller;

import com.xml.agentback.DTO.CarModelDTO;
import com.xml.agentback.DTO.FuelTypeDTO;
import com.xml.agentback.model.CarModel;
import com.xml.agentback.model.FuelType;
import com.xml.agentback.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("fuelType")
public class FuelTypeController {

    @Autowired
    private FuelTypeService fuelTypeService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<FuelType> fuelTypes = this.fuelTypeService.getAll();
            List<FuelTypeDTO> fuelTypeDTOs = new ArrayList<>();
            for(FuelType fuelType : fuelTypes){
                fuelTypeDTOs.add(new FuelTypeDTO(fuelType));
            }
            return new ResponseEntity<>(fuelTypeDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getFuelType(@PathVariable("id") Long id){
        FuelType retVal = fuelTypeService.getOne(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addFuelType(@RequestBody FuelTypeDTO fuelTypeDTO){
        FuelType retVal = this.fuelTypeService.addOne(new FuelType(fuelTypeDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteFuelType(@PathVariable("id") Long id) {

        try {
            FuelType fuelType = this.fuelTypeService.getOne(id);
            if (fuelType != null) {
                this.fuelTypeService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateFuelType(@RequestBody FuelTypeDTO fuelTypeDTO){
        FuelType retVal = this.fuelTypeService.update(new FuelType(fuelTypeDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
