package com.xml.agentback.service.impl;

import com.xml.agentback.DTO.MessageDTO;
import com.xml.agentback.model.Message;
import com.xml.agentback.model.User;
import com.xml.agentback.repository.MessageRepository;
import com.xml.agentback.repository.UserRepository;
import com.xml.agentback.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Message> getAll(Long userId) {
        List<Message> messages = this.messageRepository.findAllByReceiverId(userId);
        Collections.sort(messages);
        return messages;
    }

    @Override
    public List<Message> getMessageList(Long receiverId, String username) {
        User sender = this.userRepository.findByUsername(username);
        List<Message> messages = this.messageRepository.findMessageList(receiverId, sender.getId());
        //sort by date
        Collections.sort(messages);
        return messages;
    }

    @Override
    public Message getOne(Long id) {
        return this.messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message addOne(MessageDTO messageDTO) {
        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setTimeSent(new Date());
        message.setReceiver(this.userRepository.findByUsername(messageDTO.getReceiver()));
        message.setSender(this.userRepository.findByUsername(messageDTO.getSender()));
        return this.messageRepository.save(message);
    }

    @Override
    public void deleteById(Long id) {

        this.messageRepository.deleteById(id);
    }
}
