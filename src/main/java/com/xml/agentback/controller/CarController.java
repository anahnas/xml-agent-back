package com.xml.agentback.controller;


import com.xml.agentback.DTO.CarDTO;
import com.xml.agentback.model.Car;
import com.xml.agentback.model.Rental;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_PLAIN;

@RestController
@RequestMapping("car")
@CrossOrigin("http://localhost:4200")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            List<Car> cars = this.carService.getAll();
            List<CarDTO> carDTOs = new ArrayList<>();
            for(Car car : cars) {
                carDTOs.add(new CarDTO(car));
            }
            return new ResponseEntity<>(carDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping( value="/{id}" )
    public ResponseEntity<?> getCar(@PathVariable Long id){
        Car car = carService.getOne(id);
        if(car != null){
            CarDTO retVal = new CarDTO(car);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping( value="/notAvailable" )
    public ResponseEntity<?> notAvailable(@RequestBody Rental rental) {
        Rental r = this.carService.blockCar(rental);
        if( r == null )
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(  HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody CarDTO carDTO){
        System.out.println(carDTO.getId());
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

    @GetMapping( value ="/{id}/rentals")
    public ResponseEntity<?> allRentals(@PathVariable Long id){
        ArrayList<Rental> rentals= this.carService.allRentals(id);
        if(rentals != null) {
           return new ResponseEntity<>(rentals, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping( value = "/{carid}/carCalendar")
    public ResponseEntity<?> findCarCalendarId(@PathVariable Long carid) {
        Long carCalendarId = this.carService.findCarCalendar(carid);
        if(carCalendarId != null) {
            return new ResponseEntity<>(carCalendarId, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Long id) throws IOException {
        return this.carService.getImage(id);
        //return IOUtils.toByteArray(in);

    }
}
