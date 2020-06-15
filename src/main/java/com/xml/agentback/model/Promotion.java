package com.xml.agentback.model;

import com.xml.agentback.DTO.PromotionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    Car car;

    public Promotion() {
    }

    public Promotion(PromotionDTO promotionDTO) {
        if(promotionDTO.getId() != null)
            this.id = promotionDTO.getId();
    }

}
