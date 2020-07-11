package com.xml.agentback.model;

import com.xml.agentback.DTO.MessageDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Message implements Comparable<Message>{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User receiver;
    @ManyToOne
    private User sender;
    private String content;
    private Date timeSent;
    private String usernameBack;

    public Message(){}

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", sender=" + sender +
                ", content='" + content + '\'' +
                ", timeSent=" + timeSent +
                '}';
    }

    @Override
    public int compareTo(Message message) {
        if (getTimeSent() == null || message.getTimeSent() == null) {
            return 0;
        }
        return getTimeSent().compareTo(message.getTimeSent());
    }

}
