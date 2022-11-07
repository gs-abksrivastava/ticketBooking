package com.gainsight.ticketbookingmultimodule.service;


import com.gainsight.ticketbookingmultimodule.dto.DtoUtility;
import com.gainsight.ticketbookingmultimodule.dto.TicketDto;
import com.gainsight.ticketbookingmultimodule.repository.TicketRepo;
import com.gainsight.ticketbookingmultimodule.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DtoUtility dtoUtility;

    public List<TicketDto> getTickets(){
        log.info("Fetching all tickets from DB");
        List<Ticket> tickets = ticketRepo.findAll();
        List<TicketDto> ticketDtos = dtoUtility.convertTicketListToDtoList(tickets);
        return ticketDtos;
    }



    public boolean bookTicket(TicketDto ticketDto){
        log.info("Saving ticket info for ticketNo : {}",ticketDto.getTicketNo());
        Ticket ticket = dtoUtility.convertTicketDtoToTicket(ticketDto);
        Ticket existingTicket = ticketRepo.findByTicketNo(ticket.getTicketNo());
        boolean isTicketBooked;
        if(existingTicket != null) {
              isTicketBooked = false;
        }else {
            ticketRepo.save(ticket);
            isTicketBooked = true;
        }
        return isTicketBooked;
    }


    @Override
    public void publishTicket(TicketDto ticketDto) {
        log.info("Publishing ticket info for ticketNo : {}",ticketDto.getTicketNo());
        log.info(String.valueOf(ticketDto));
        rabbitTemplate.convertAndSend("bootcamper-sri.exchange", ticketDto.getType().toString(),ticketDto);

    }
}
