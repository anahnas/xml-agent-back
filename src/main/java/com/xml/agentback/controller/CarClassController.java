package com.xml.agentback.controller;

import com.xml.agentback.DTO.CarClassDTO;
import com.xml.agentback.model.CarClass;
import com.xml.agentback.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carClass")
public class CarClassController {

    @Autowired
    private CarClassService carClassService;

    @GetMapping
    public ResponseEntity<List<CarClassDTO>> getAll() {
        try {
            List<CarClass> carClasses = this.carClassService.getAll();
            List<CarClassDTO> carClassDTOs = new ArrayList<>();
            for(CarClass carClass : carClasses){
                carClassDTOs.add(new CarClassDTO(carClass));
            }
            return new ResponseEntity<>(carClassDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCarClass(@PathVariable Long id){
        Optional<CarClass> retVal = Optional.ofNullable(carClassService.getOne(id));
        if(retVal != null){
            CarClassDTO retValDTO = new CarClassDTO(retVal.get());
            return new ResponseEntity<>(retValDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteCarClass(@PathVariable Long id) {

        try {
            Optional<CarClass> carClass = Optional.ofNullable(this.carClassService.getOne(id));
            if (carClass != null) {
                this.carClassService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCarClass(@RequestBody CarClassDTO carClassDTO){

        CarClass retVal = this.carClassService.addOne(new CarClass(carClassDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> updateCarClass(@RequestBody CarClassDTO carClassDTO){
        CarClass retVal = this.carClassService.update(new CarClass(carClassDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
