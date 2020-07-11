package com.xml.agentback.service;

import com.xml.agentback.DTO.MessageDTO;
import com.xml.agentback.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAll(Long userId);

    List<Message> getMessageList(Long receiverId, String sender);

    Message getOne(Long id);

    Message addOne(MessageDTO messageDTO);

    void deleteById(Long id);

    void save(Message message);
}
