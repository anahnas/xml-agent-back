package com.xml.agentback.repository;

import com.xml.agentback.model.CarCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarCalendarRepository extends JpaRepository<CarCalendar, Long> {
    Optional<CarCalendar> findById(Long id);
    CarCalendar findByCarId(Long carId);
    CarCalendar save(CarCalendar carCalendar);
}
