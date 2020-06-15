package com.xml.agentback.repository;

import com.xml.agentback.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    ArrayList<Rental> findByCarCalendarId(Long id);

    @Query("SELECT r FROM Rental r WHERE NOT (?1 > r.startDate and ?2 < r.endDate) and NOT(?1 > r.startDate and ?1 < r.endDate) and NOT (?2 > r.startDate and ?2 < r.endDate) and NOT (?1 < r.startDate and ?2 > r.endDate)")
    ArrayList<Rental> findFree(Date startDate, Date endDate);

    Rental save(Rental rental);

    @Query("SELECT r FROM Rental r WHERE (?1 = r.carCalendarId)")
    ArrayList<Rental> findAllById(Long id);

    @Modifying
    @Query("DELETE FROM Rental r where r.id in ?1")
    void deleteRentalsWithIds(List<Long> ids);

    Rental deleteById(List<Long> id);

}
