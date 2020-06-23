package com.xml.agentback.service;

import com.xml.agentback.DTO.AdvertisementDTO;
import com.xml.agentback.DTO.StatisticDTO;
import com.xml.agentback.model.Advertisement;
import com.xml.agentback.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface AdvertisementService {

    ArrayList<AdvertisementDTO> getAll();
    void add(Advertisement ad);
    Advertisement save(Advertisement advertisement);
    Long newAdvertisement(AdvertisementDTO advertisementDTO, Long userId);
    AdvertisementDTO getOneAd(Long id);
    List<Advertisement> findAdvertisersAds(Long advertiserId);
    void uploadImage(MultipartFile image) throws IOException;

}
