package com.xml.agentback.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AdvertisementDTO {

    private Long id;
    private CarDTO carDTO;
    private UserDTO userDTO;
    private Date startDate;
    private Date endDate;
}
