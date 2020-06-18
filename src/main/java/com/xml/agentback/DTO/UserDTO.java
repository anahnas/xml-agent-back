package com.xml.agentback.DTO;

import com.xml.agentback.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;

    public UserDTO() {}

    public UserDTO(String username) {
        this.username = username;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
