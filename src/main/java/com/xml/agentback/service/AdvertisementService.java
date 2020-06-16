package com.xml.agentback.service;

import com.xml.agentback.DTO.AdvertisementDTO;
import com.xml.agentback.model.Advertisement;

import java.util.ArrayList;
import java.util.List;

public interface AdvertisementService {

    ArrayList<AdvertisementDTO> getAll();
    void add(Advertisement ad);
    Advertisement save(Advertisement advertisement);
    Advertisement newAdvertisement(AdvertisementDTO advertisementDTO);
    AdvertisementDTO getOneAd(Long id);
    List<Advertisement> findAdvertisersAds(Long advertiserId);
}
