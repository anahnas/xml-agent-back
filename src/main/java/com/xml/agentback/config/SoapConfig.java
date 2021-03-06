package com.xml.agentback.config;

import com.xml.agentback.soap.AdClient;
import com.xml.agentback.soap.MessageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.xml.RentACar.wsdl");
        return marshaller;
    }

    @Bean
    public AdClient adClient(Jaxb2Marshaller marshaller) {
        AdClient client = new AdClient();
        client.setDefaultUri("http://localhost:8081/car-service-schema/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public MessageClient messageClient(Jaxb2Marshaller marshaller) {
        MessageClient client = new MessageClient();
        client.setDefaultUri("http://localhost:8081/xml-user-service-schema/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
