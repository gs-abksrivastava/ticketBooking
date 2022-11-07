package com.gainsight.ticketbookingmultimodule.entity;



import lombok.Data;
import com.gainsight.ticketbookingmultimodule.dto.TicketDto;

@Data
public class Response {
    private TicketDto ticketDto;
    private String statusMsg;

}
