package com.xml.agentback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false) //zeza table name dont know why, tried everything
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;

    @Column
    private Long advertiserId;

    @Column
    private Date startDate;

    @Column
    private Date endDate;


}
