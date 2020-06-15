package com.xml.agentback.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransmissionDTO {
    private String type;

    public TransmissionDTO() {
    }

    public TransmissionDTO(String type) {
        this.type = type;
    }
}
