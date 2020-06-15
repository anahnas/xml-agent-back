package com.xml.agentback.service.impl;

import com.xml.agentback.model.Car;
import com.xml.agentback.model.CarCalendar;
import com.xml.agentback.model.Rental;
import com.xml.agentback.repository.CarCalendarRepository;
import com.xml.agentback.repository.CarRepository;
import com.xml.agentback.repository.RentalRepository;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarCalendarRepository carCalendarRepository;

    @Override
    public Car addOne(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car getOne(Long id) {
        if(this.carRepository.findById(id).isPresent())
            return this.carRepository.findById(id).get();
        else
            return null;
    }
    
    @Override
    public Car update(Car car) {
        Car toUpdate = this.carRepository.getOne(car.getId());

        toUpdate.setCarModel(car.getCarModel());
        car.setFuelType(car.getFuelType());
        car.setTransmission(car.getTransmission());
        car.setCarClass(car.getCarClass());
        car.setPricePerDay(car.getPricePerDay());
        car.setPricePerKm(car.getPricePerKm());
        car.setLimitedKms(car.isLimitedKms());
        car.setLimitKmsPerDay(car.getLimitKmsPerDay());
        car.setKmage(car.getKmage());
        car.setWaiver(car.isWaiver());
        car.setAvailableChildSeats(car.getAvailableChildSeats());
        car.setOwner(car.getOwner());
        car.setCarRatings(car.getCarRatings());
        car.setPromotions(car.getPromotions());

        return this.carRepository.save(car);
    }
    
    @Override
    public boolean deleteById(Long id) {
        try{
            this.carRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Rental blockCar(Rental rental) {

        try {

            CarCalendar carCal = carCalendarRepository.getOne(rental.getCarCalendarId());

            List<Rental> rentals = this.rentalRepository.findAllById(carCal.getId());
            ArrayList<Long> deleteListId = new ArrayList();

            for (Rental r : rentals) {
                if ((r.getStartDate().after(rental.getStartDate()) || r.getStartDate().equals(rental.getStartDate())) && (r.getEndDate().before(rental.getEndDate()) || r.getEndDate().equals(rental.getEndDate()))) {
                    // Moze da se zakazati (unutar termina).
                    deleteListId.add(r.getId());
                    continue;
                } else if ((r.getStartDate().before(rental.getStartDate())) && (r.getEndDate().before(rental.getEndDate()) && r.getEndDate().after(rental.getStartDate()))) {
                    // Ne valja - ne moze se zakazati (gornja granica je unutar termina), automobil vec izdat.
                    return null;
                } else if ((r.getStartDate().before(rental.getEndDate()) && r.getStartDate().after(rental.getStartDate()) && r.getEndDate().after(rental.getEndDate()))) {
                    // Ne valja - ne moze se zakazati (donja granica je unutar termina).
                    return null;
                }
            }

            // Brisanje liste rentala
            this.rentalRepository.deleteRentalsWithIds(deleteListId);

            // Cuvanje novog rentala i njegovo dodavanje
            Rental rent = this.rentalRepository.save(rental);
            carCal.getRentalIds().add(rent.getId());

            this.carCalendarRepository.save(carCal);

            return rent;

        } catch (Exception e){

            e.printStackTrace();
            return null;
        }

    }


}
