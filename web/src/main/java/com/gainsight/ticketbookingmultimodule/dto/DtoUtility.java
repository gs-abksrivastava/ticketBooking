package com.gainsight.ticketbookingmultimodule.dto;



import org.springframework.stereotype.Component;
import com.gainsight.ticketbookingmultimodule.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoUtility {

    public TicketDto convertTicketToTicketDto(Ticket ticket){
        TicketDto ticketDto = TicketDto.builder()
                .ticketNo(ticket.getTicketNo())
                .bookingTime(ticket.getBookingTime())
                .status(ticket.getStatus())
                .type(ticket.getType())
                .build();

        return ticketDto;
    }

    public Ticket convertTicketDtoToTicket(TicketDto ticketDto){
        Ticket ticket = Ticket.builder()
                .ticketNo(ticketDto.getTicketNo())
                .bookingTime(ticketDto.getBookingTime())
                .status(ticketDto.getStatus())
                .type(ticketDto.getType())
                .build();

        return ticket;
    }

    public List<TicketDto> convertTicketListToDtoList(List<Ticket> tickets){
        List<TicketDto> ticketDtos = new ArrayList<>();
        for(Ticket ticket :tickets){
            ticketDtos.add(convertTicketToTicketDto(ticket));
        }
        return ticketDtos;
    }

}
