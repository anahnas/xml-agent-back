package com.xml.agentback.DTO;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private String name;
    private double kmage;
    private double rating;
    private Long comment;
}
