package com.xml.agentback.DTO;

import com.xml.agentback.model.Transmission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransmissionDTO {
    private Long id;
    private String type;

    public TransmissionDTO() {
    }

    public TransmissionDTO(String type) {
        this.type = type;
    }

    public TransmissionDTO(Transmission transmission) {
        this.id = transmission.getId();
        this.type = transmission.getType();
    }
}
