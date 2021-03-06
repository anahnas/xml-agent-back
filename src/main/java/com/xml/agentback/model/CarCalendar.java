package com.xml.agentback.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class CarCalendar {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    @ElementCollection(targetClass=Long.class)
    private List<Long> rentalIds;

    public CarCalendar() {
    }

}
