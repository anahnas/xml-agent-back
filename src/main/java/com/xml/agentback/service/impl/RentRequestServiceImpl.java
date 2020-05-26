package com.xml.agentback.service.impl;

import com.xml.agentback.model.RentRequest;
import com.xml.agentback.model.RentStatus;
import com.xml.agentback.repository.RentRequestRepository;
import com.xml.agentback.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    private RentRequestRepository rentRequestRepository;


    @Override
    public List<RentRequest> getCancelableRR() {
        return this.rentRequestRepository.getCancellableReqs();
    }


    @Override
    public List<RentRequest> getAll() {
        return this.rentRequestRepository.findAll();
    }

    @Override
    public RentRequest getOne(Long id) {
        return this.rentRequestRepository.getOne(id);
    }

    @Override
    public List<RentRequest> getPaidRentReqs() {
        return this.rentRequestRepository.getPaidRentReqs();
    }

    @Override
    public List<RentRequest> getPendingReqs() {
        return this.rentRequestRepository.getPendingReqs();
    }

    @Override
    public void createRentReq(RentRequest rentreq) {

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime fromDateAndTime = LocalDateTime.of(currentDate, currentTime);
        rentreq.setStartDate(fromDateAndTime);

        LocalDateTime aDateTime = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
        rentreq.setEndDate(aDateTime);

        rentreq.setRentStatus(RentStatus.PENDING);

        // rentreq.setClientId();

        this.rentRequestRepository.save(rentreq);

    }
}
