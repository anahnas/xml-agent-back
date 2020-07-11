package com.xml.agentback.soap;

import com.xml.RentACar.wsdl.GetMessagesRequest;
import com.xml.RentACar.wsdl.GetMessagesResponse;
import com.xml.RentACar.wsdl.MessageRequest;
import com.xml.RentACar.wsdl.MessageResponse;
import com.xml.agentback.DTO.MessageDTO;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.io.IOException;
import java.util.Date;

public class MessageClient extends WebServiceGatewaySupport {

    public MessageResponse sendMessage(MessageDTO messageDto) {

        System.out.println("MESSAGE DTO AS PARAMETER IN RESPONSE CLIENT " + messageDto.toString());

        MessageRequest request = new MessageRequest();

        request.setContent(messageDto.getContent());
        request.setDateSent(new Date().toString());
        request.setReciever(messageDto.getReceiver());
        // request.setSender("Mike");
        request.setSender("5");

        System.out.println("STA SALJEMO U REQUEST ZA GLAVNI BACK: " + request.getContent() + ", " + request.getSender() + ", " + request.getReciever() + ", " + request.getDateSent());

        MessageResponse response = (MessageResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/xml-user-service-schema", request,
                        new SoapActionCallback("http://localhost:8085/ws/xml-user-service-schema/messageRequest"));

        return response;
    }

    public GetMessagesResponse getMessages() throws IOException {

        System.out.println("USLO U GET MESSAGES U CLIENTU");
        GetMessagesRequest request = new GetMessagesRequest();
        request.setSender("5");

        GetMessagesResponse response = (GetMessagesResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/xml-user-service-schema", request,
                        new SoapActionCallback("http://localhost:8085/ws/xml-user-service-schema/getMessagesRequest"));

        System.out.println("KRAJ MESSAGE CLIENTA");

        return response;

    }
}
