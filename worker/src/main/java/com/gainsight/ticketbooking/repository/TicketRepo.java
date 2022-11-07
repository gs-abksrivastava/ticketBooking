package com.gainsight.ticketbooking.repository;


import com.gainsight.ticketbooking.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepo extends MongoRepository<Ticket,String> {
    public Ticket findByTicketNo(int ticketNo);
}
