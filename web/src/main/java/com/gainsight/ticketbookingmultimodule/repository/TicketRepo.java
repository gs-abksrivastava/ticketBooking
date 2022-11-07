package com.gainsight.ticketbookingmultimodule.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import com.gainsight.ticketbookingmultimodule.entity.Ticket;

public interface TicketRepo extends MongoRepository<Ticket,String> {
    public Ticket findByTicketNo(int ticketNo);
}
