package com.xml.agentback.service;

import com.xml.agentback.model.RentRequest;

import java.util.List;

public interface RentRequestService {

    List<RentRequest> getAll();

    RentRequest getOne(Long id);

    List<RentRequest> getPaidRentReqs();

    List<RentRequest> getPendingReqs();

    List<RentRequest> getCancelableRR();



    void createRentReq(RentRequest rentreq);
}
