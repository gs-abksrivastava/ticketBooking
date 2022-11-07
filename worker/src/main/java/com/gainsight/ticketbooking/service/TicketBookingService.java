package com.gainsight.ticketbooking.service;

import com.gainsight.ticketbooking.entity.Status;
import com.gainsight.ticketbooking.entity.Ticket;
import com.gainsight.ticketbooking.repository.TicketRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketBookingService {

    @Autowired
    private TicketRepo ticketRepo;

    Logger log = LoggerFactory.getLogger(this.getClass());
    public void bookTicket(Ticket ticket){
        Ticket ticketFromDB = ticketRepo.findByTicketNo(ticket.getTicketNo());
        if(ticketFromDB == null){
            ticketRepo.save(ticket);
            log.info("Ticket with Id -> {} saved to database",ticket.getTicketNo());
        }else {
            if(ticket.getStatus() == Status.INPROCESS){
                log.info("Ticket with ticket Id -> {} already {}",ticket.getTicketNo(),ticketFromDB.getStatus());
            }else if(ticket.getStatus() == Status.BOOKED){
                if(ticketFromDB.getStatus() == Status.INPROCESS){
                    ticketRepo.save(ticket);
                    log.info("Congrats your ticket is booked and its TicketId is {}",ticket.getTicketNo());
                }else{
                    log.info("Ticket with ticket Id -> {} already {}",ticket.getTicketNo(),ticketFromDB.getStatus());
                }
            }else if(ticket.getStatus() == Status.FAILED){
                if(ticketFromDB.getStatus() == Status.INPROCESS){
                    ticketRepo.save(ticket);
                    log.info("Oops there was some issue your ticket with TicketId {} failed",ticket.getTicketNo());
                }else{
                    log.info("Ticket with ticket Id -> {} already {}",ticket.getTicketNo(),ticketFromDB.getStatus());
                }

            }
        }
//        System.out.println(ticketFromDB);

    }
}
