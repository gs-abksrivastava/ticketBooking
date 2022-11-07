package com.gainsight.ticketbooking.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gainsight.ticketbooking.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Listener implements MessageListener {

    @Autowired
    private TicketBookingService ticketBookingService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message) {
//        System.out.println("Message - "+ message);
        String json = new String(message.getBody());
        Ticket ticket;
        ObjectMapper mapper = new ObjectMapper();
        try {
            ticket = mapper.readValue(json,Ticket.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Queue -> [{}], Type -> [{}], Ticket Id -> [{}] ",message.getMessageProperties().getConsumerQueue(),ticket.getType(),ticket.getTicketNo());
        ticketBookingService.bookTicket(ticket);
//        System.out.println(ticket.getTicketNo());
    }
}
