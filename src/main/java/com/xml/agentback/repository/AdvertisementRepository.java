package com.xml.agentback.repository;

import com.xml.agentback.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Override
    List<Advertisement> findAll();

    @Override
    Optional<Advertisement> findById(Long aLong);

    @Query(value = "select a from Advertisement a where a.advertiserId = ?1 ")
    List<Advertisement> findAdsByAdvertiserId(Long advertiserId);


}


