package com.xml.agentback.service;

import com.xml.agentback.DTO.CarDTO;
import com.xml.agentback.DTO.StatisticDTO;
import com.xml.agentback.model.Car;
import com.xml.agentback.model.Rental;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public interface CarService {

    List<Car> getAll();

    Car getOne(Long id);

    Car addOne(Car car);

    Car update(Car car);

    boolean deleteById(Long id);

    Rental blockCar(Rental rental);

    ArrayList<Rental> allRentals(Long id);

    Long findCarCalendar(Long id);
  
    void setImagePath(String path, String originalFilename);

    byte[] getImage(Long id) throws IOException;

    Double getRentKmage(Long carId);
    List<StatisticDTO> getKmage(Long id);
    List<StatisticDTO> getRatings(Long id);

}
