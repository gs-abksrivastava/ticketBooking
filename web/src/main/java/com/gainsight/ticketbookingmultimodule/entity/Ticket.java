package com.gainsight.ticketbookingmultimodule.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@Document(collection = "TicketStatus")
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
//    @Indexed(unique = true)

    @Id
    private int ticketNo;
    private Status status;
    private Date bookingTime;
    private Type type;


}
