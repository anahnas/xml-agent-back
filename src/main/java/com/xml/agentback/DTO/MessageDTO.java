package com.xml.agentback.DTO;

import com.xml.agentback.model.Message;
import com.xml.agentback.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class MessageDTO {

    private Long id;
    private String receiver;
    private String sender;
    private String content;
    private String timeSent;
    private Long mainId;
    public MessageDTO() {
    }

    public MessageDTO(Message message) {
        if(message.getId() != null)
            this.id = message.getId();
        this.receiver = message.getReceiver().getUsername();
        if(message.getSender() == null) {
            this.sender = message.getUsernameBack();
        } else {
            this.sender = message.getSender().getUsername();

        }
        this.content = message.getContent();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strDate = dateFormat.format(message.getTimeSent());
        this.timeSent = strDate;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", timeSent='" + timeSent + '\'' +
                '}';
    }
}
