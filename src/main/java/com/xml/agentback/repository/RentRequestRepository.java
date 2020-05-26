package com.xml.agentback.repository;

import com.xml.agentback.model.RentRequest;
import com.xml.agentback.model.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {

    @Query(value = "SELECT * FROM rent_request r WHERE r.status = 'PAID'", nativeQuery = true)
    List<RentRequest> getPaidRentReqs();

    @Query(value = "SELECT * FROM rent_request r WHERE r.status = 'PENDING'", nativeQuery = true)
    List<RentRequest> getPendingReqs();

    @Query(value = "SELECT * FROM rent_request r WHERE r.status = 'PAID' or r.status = 'RESERVED'", nativeQuery = true)
    List<RentRequest> getCancellableReqs();

    @Query(value = "SELECT rr from RentRequest rr where rr.id = ?1")
    RentRequest find(Long id);

    @Override
    Optional<RentRequest> findById(Long aLong);
}
