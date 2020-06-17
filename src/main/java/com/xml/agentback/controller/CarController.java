package com.xml.agentback.controller;


import com.xml.agentback.DTO.CarDTO;
import com.xml.agentback.model.Car;
import com.xml.agentback.model.Rental;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            List<Car> cars = this.carService.getAll();
            List<CarDTO> carDTOs = new ArrayList<>();
            for(Car car : cars){
                carDTOs.add(new CarDTO(car));
            }
            return new ResponseEntity<>(carDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id){
        Car car = carService.getOne(id);
        if(car != null){
            CarDTO retVal = new CarDTO(car);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/notAvailable")
    public ResponseEntity<?> notAvailable(@RequestBody Rental rental) {
        Rental r = this.carService.blockCar(rental);
        if( r == null )
            return new ResponseEntity<>("Car blocking error!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>( "Car is not available any more for requested period of time.", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody CarDTO carDTO){
        Car car = new Car(carDTO);
        car = this.carService.addOne(car);
        if(car != null)
            return new ResponseEntity<>(car, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> updateCar(@RequestBody CarDTO carDTO){
        Car retVal = this.carService.update(new Car(carDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
