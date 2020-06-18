package com.xml.agentback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.agentback.DTO.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name="user_table")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToMany
    private Set<Car> cars;
    @OneToMany
    @JsonIgnore
    private Set<CarRating> carRatings;

    public User() {
        carRatings = new HashSet<>();
        cars = new HashSet<>();
    }

    public User(Long id) {
        this.id = id;
        carRatings = new HashSet<>();
        cars = new HashSet<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        carRatings = new HashSet<>();
        cars = new HashSet<>();
    }
    public User(UserDTO userDTO) {
        if(userDTO.getId() != null)
            this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        carRatings = new HashSet<>();
        cars = new HashSet<>();
    }


}


