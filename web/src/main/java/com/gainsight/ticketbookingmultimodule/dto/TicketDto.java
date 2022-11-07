package com.gainsight.ticketbookingmultimodule.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gainsight.ticketbookingmultimodule.entity.Status;
import com.gainsight.ticketbookingmultimodule.entity.Ticket;
import com.gainsight.ticketbookingmultimodule.entity.Type;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDto implements Serializable{

    private int ticketNo;
    private Status status;
    private Date bookingTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Type type;

    public static void main(String[] args) {
        System.out.println(new Date());
        Ticket ticket = new Ticket(22,Status.INPROCESS,new Date(),Type.student);
        System.out.println(ticket);
    }



}
