package com.gainsight.ticketbookingmultimodule.service;





import com.gainsight.ticketbookingmultimodule.dto.TicketDto;

import java.util.List;


public interface BookingService {
    public boolean bookTicket(TicketDto ticketDto);
    public List<TicketDto> getTickets();
    public void publishTicket(TicketDto ticketDto);
}
