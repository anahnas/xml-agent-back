package com.xml.agentback.repository;

import com.xml.agentback.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAll();

    List<Message> findAllByReceiverId(Long id);

    @Query("SELECT m FROM Message m WHERE ((?1 = m.receiver.id) and (?2 = m.sender.id)) or ((?2 = m.receiver.id) and (?1 = m.sender.id))")
    List<Message> findMessageList(Long receiverId, Long senderId);

    void deleteById(Long id);

    Message save(Message message);

    List<Message> findAllByUsernameBack(String usernameBack);
}
