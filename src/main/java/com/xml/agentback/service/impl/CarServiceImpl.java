package com.xml.agentback.service.impl;

import com.xml.agentback.DTO.StatisticDTO;
import com.xml.agentback.model.Car;
import com.xml.agentback.model.CarCalendar;
import com.xml.agentback.model.CarRating;
import com.xml.agentback.model.Rental;
import com.xml.agentback.repository.CarCalendarRepository;
import com.xml.agentback.repository.CarRatingRepository;
import com.xml.agentback.repository.CarRepository;
import com.xml.agentback.repository.RentalRepository;
import com.xml.agentback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRatingRepository carRatingRepository;

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
            System.out.println("Today: " + todayString);
            Date today = formatter.parse(todayString);


            CarCalendar carCal = carCalendarRepository.getOne(rental.getCarCalendarId());

            List<Rental> rentals = this.rentalRepository.findAllById(carCal.getId());
            ArrayList<Long> deleteListId = new ArrayList();

            for (Rental r : rentals) {
                if(((r.getStartDate().before(today) || (r.getStartDate().equals(today))) && r.getEndDate().after(today)) && !(startD.after(r.getEndDate()))) {
                    System.out.println("Automobil je vec izdat nekom i placen!");
                    return null;
                } else if (startD.before(today) || endD.before(today)) {
                    System.out.println("Ne mozete iznajmiti automobil u proslosti!");
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

    public ArrayList<Rental> allRentals(Long id) {
        CarCalendar carCalendar = this.carCalendarRepository.findByCarId(id);
        ArrayList<Rental> rentals = this.rentalRepository.findByCarCalendarId(carCalendar.getId());
        if (rentals != null) {
            return rentals;
        }

        return null;
    }

    public Long findCarCalendar(Long id) {
        CarCalendar carCalendar = this.carCalendarRepository.findByCarId(id);
        Long carCalendarId = carCalendar.getId();
        if( carCalendarId != null ) {
            return carCalendarId;
        }

        return null;
    }
  
    @Override
    public void setImagePath(String path, String originalFileName){
        //getting the car id from uploaded image
        String carId = originalFileName.split("-")[0];
        Car car = this.carRepository.findById(Long.valueOf(carId)).get();
        car.setImagePath(path);
        carRepository.save(car);
    }

    public byte[] getImage(Long id) throws IOException {
        String path = this.carRepository.getOne(id).getImagePath();

        if(path != null)
            return Files.readAllBytes(Paths.get(path));
        else
            return null;
    }

    @Override
    public Double getRentKmage(Long carId) {
        //namjesti kad je isti rentiran vise putaaa
       /* List<Car> cars = this.carRepository.findAll();
        Double kmage = 0.0;
        for(Car car : cars) {
            kmage += car.getKmage();
        }
        return kmage;*/
        Car car = this.carRepository.getOne(carId);
        Double kmage = car.getKmage();
        return kmage;
    }

    @Override
    public List<StatisticDTO> getKmage(Long id) {
        List<StatisticDTO> statistics = new ArrayList<StatisticDTO>();
        List<Car> cars = this.carRepository.findAllById(id);
        for (Car c : cars) {
            StatisticDTO statisticDTO = new StatisticDTO();
            statisticDTO.setName(c.getCarModel().getName());
            statisticDTO.setKmage(c.getKmage());
            statistics.add(statisticDTO);
        }
        statistics.sort(Comparator.comparing(StatisticDTO::getKmage).reversed());
        return statistics;
    }

    @Override
    public List<StatisticDTO> getRatings(Long id) {
            List<StatisticDTO> statistics = new ArrayList<StatisticDTO>();
            List<CarRating> ratings = this.carRatingRepository.findAllByCarId(id);
            for (CarRating cr : ratings) {
                StatisticDTO stat = new StatisticDTO();
                stat.setName(cr.getCar().getCarModel().getName());
                stat.setRating(cr.getRating());
                System.out.println("***RATING: " + cr.getRating());
                statistics.add(stat);
            }
            statistics.sort(Comparator.comparing(StatisticDTO::getRating).reversed());
            return statistics;

    }

}
