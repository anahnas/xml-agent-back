package com.xml.agentback.DTO;

import com.xml.agentback.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String password;

    public UserDTO() {}

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
