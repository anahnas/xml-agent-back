package com.xml.agentback.model;

import com.xml.agentback.DTO.TransmissionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Transmission {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;

    public Transmission() {
    }

    public Transmission(String type) {
        this.type = type;
    }

    public Transmission(TransmissionDTO transmissionDTO) {
        if(transmissionDTO.getId() != null)
            this.id = transmissionDTO.getId();
        this.type = transmissionDTO.getType();
    }
}
