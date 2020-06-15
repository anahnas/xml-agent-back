package com.xml.agentback.DTO;

import com.xml.agentback.model.Promotion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionDTO {
    private Long id;

    public PromotionDTO(){}

    public PromotionDTO(Promotion promotion){
        this.id = promotion.getId();
    }
}
