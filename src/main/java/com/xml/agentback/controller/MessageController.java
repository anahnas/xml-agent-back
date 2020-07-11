package com.xml.agentback.controller;

import com.xml.RentACar.wsdl.MessageResponse;
import com.xml.agentback.DTO.MessageDTO;
import com.xml.agentback.model.Message;
import com.xml.agentback.model.RentRequest;
import com.xml.agentback.service.CarService;
import com.xml.agentback.service.MessageService;
import com.xml.agentback.service.RentRequestService;
import com.xml.agentback.soap.MessageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("messages")
@CrossOrigin("http://localhost:4200")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageClient messageClient;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader ("receiverId") Long receiverId) {
        try {
            List<Message> messages = this.messageService.getAll(receiverId);
            List<MessageDTO> messageDTOs = new ArrayList<>();
            for (Message message : messages){
                messageDTOs.add(new MessageDTO(message));
            }
            return new ResponseEntity<>(messageDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/{sender}")
    public ResponseEntity<?> getMessageList(@RequestHeader ("receiverId") Long userId, @PathVariable String sender) {
        try {
            List<Message> messages = this.messageService.getMessageList(userId, sender);
            List<MessageDTO> messageDTOs = new ArrayList<>();
            for (Message message : messages){
                messageDTOs.add(new MessageDTO(message));
            }
            return new ResponseEntity<>(messageDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            Message message = this.messageService.getOne(id);
            return new ResponseEntity<>(new MessageDTO(message), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {
            System.out.println("***WHAT'S MESSAGE DTO FOR SENDING " + messageDTO.toString() );
            // this.messageClient.sendMessage(messageDTO);
            MessageResponse response = messageClient.sendMessage(messageDTO);
           // System.out.println("*** RESPONSE" + response.toString());


            this.messageService.addOne(messageDTO);
            System.out.println("*****USAO U SLANJE PORUKE ZA SOAP *****" + messageDTO.toString());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("***** NIJE USAO U SLANJE PORUKE*****");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
