package com.xml.agentback.model;

import com.xml.agentback.DTO.PromotionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Promotion() {
    }

    public Promotion(PromotionDTO promotionDTO) {
        if(promotionDTO.getId() != null)
            this.id = promotionDTO.getId();
    }

}
