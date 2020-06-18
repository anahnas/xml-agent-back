package com.xml.agentback.service.impl;

import com.xml.agentback.model.Car;
import com.xml.agentback.model.CarCalendar;
import com.xml.agentback.model.Rental;
import com.xml.agentback.repository.CarCalendarRepository;
import com.xml.agentback.repository.CarRepository;
import com.xml.agentback.repository.RentalRepository;
import com.xml.agentback.service.CarService;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    @Transactional
    public Rental blockCar(Rental rental) {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = formatter.format(rental.getStartDate());
            String endDate = formatter.format(rental.getEndDate());

            Date startD = formatter.parse(startDate);
            Date endD = formatter.parse(endDate);
            System.out.println("Start date: " + startDate +" , End date: "+ endDate);

            String todayString = formatter.format(new Date());
            System.out.println("New date: " + todayString);
            Date today = formatter.parse(todayString);


            CarCalendar carCal = carCalendarRepository.getOne(rental.getCarCalendarId());

            List<Rental> rentals = this.rentalRepository.findAllById(carCal.getId());
            ArrayList<Long> deleteListId = new ArrayList();

            for (Rental r : rentals) {
                if(r.getStartDate().before(today) && r.getEndDate().after(today) && !(startD.after(r.getEndDate()))){
                    System.out.println("Automobil je vec izdat nekom i placen!");
                    return null;
                } else {

                    if ( r.getStartDate().after(startD)  && r.getEndDate().before(endD) ) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  1 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rental.getStartDate() + " ,  " + rental.getEndDate());
                        continue;
                    } else if ((r.getStartDate().before(startD)) && (r.getEndDate().before(endD) && r.getEndDate().after(startD))) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  2 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rental.getStartDate() + " ,  " + rental.getEndDate());
                        continue;
                    } else if (  (r.getStartDate().before(endD) && r.getStartDate().after(startD)) && r.getEndDate().after(endD)) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  3 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rental.getStartDate() + " ,  " + rental.getEndDate());
                        continue;
                    } else if (r.getStartDate().equals(startD) && (r.getEndDate().before(endD) || r.getEndDate().after(endD))) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  4 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rental.getStartDate() + " ,  " + rental.getEndDate());
                        continue;
                    } else if ( r.getStartDate().before(startD)  && r.getEndDate().after(endD) ) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  5 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rental.getStartDate() + " ,  " + rental.getEndDate());
                        continue;
                    } else if ((r.getStartDate().before(startD) || r.getStartDate().after(startD)) && r.getEndDate().equals(endD)) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  6 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rental.getStartDate() + " ,  " + rental.getEndDate());
                        continue;
                    } else {
                        System.out.println(" Proslo 7 !");
                        continue;
                    }

                }

            }

            // Brisanje liste rentala
            this.rentalRepository.deleteRentalsWithIds(deleteListId);

            // Cuvanje novog rentala i njegovo dodavanje
            System.out.println(rental.getCarCalendarId());
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
