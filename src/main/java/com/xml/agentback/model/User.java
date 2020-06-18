package com.xml.agentback.model;

import com.xml.agentback.DTO.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Set<CarRating> carRatings;

    public User() {}

    public User(Long id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(UserDTO userDTO) {
        if(userDTO.getId() != null)
            this.id = userDTO.getId();
        this.username = userDTO.getUsername();
    }


}


