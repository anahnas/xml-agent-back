package com.xml.agentback.controller;


import com.xml.agentback.DTO.TransmissionDTO;
import com.xml.agentback.model.Transmission;
import com.xml.agentback.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransmissionController {

    @Autowired
    private TransmissionService transmissionService;

    @GetMapping
    public ResponseEntity<List<Transmission>> getAll() {
        try {
            List<Transmission> transmission = this.transmissionService.getAll();
            return new ResponseEntity<>(transmission, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getTransmission(@PathVariable("id") Long id){
        Transmission retVal = transmissionService.getOne(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addTransmission(@RequestBody Transmission transmission){
        Transmission retVal = this.transmissionService.addOne(transmission);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTransmission(@PathVariable Long id) {

        try {
            Transmission transmission = this.transmissionService.getOne(id);
            if (transmission != null) {
                this.transmissionService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateFuelType(@RequestBody TransmissionDTO transmissionDTO){
        Transmission retVal = this.transmissionService.update(new Transmission(transmissionDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
