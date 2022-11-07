package com.gainsight.ticketbookingmultimodule.controller;


import com.gainsight.ticketbookingmultimodule.dto.DtoUtility;
import com.gainsight.ticketbookingmultimodule.dto.TicketDto;
import com.gainsight.ticketbookingmultimodule.entity.Response;
import com.gainsight.ticketbookingmultimodule.entity.Type;
import com.gainsight.ticketbookingmultimodule.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController("tickets")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    DtoUtility dtoUtility;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("tickets")
    public List<TicketDto> getAllTickets(){
        log.info("At getAllTickets Controller");
        return bookingService.getTickets();
    }

    @PostMapping("ticket")
    public ResponseEntity<Response> bookTicket(@RequestBody TicketDto ticketDto){
        log.info("At bookTicket Controller");
//        ticketDto.setBookingTime(LocalDateTime.now());
        ticketDto.setBookingTime(new Date());
        boolean bookingCompleted = bookingService.bookTicket(ticketDto);
        Response response = new Response();
        HttpStatus creationStatus;
        if(!bookingCompleted) {
            response.setTicketDto(ticketDto);
            response.setStatusMsg("Given ticket No. already exits, please change it");
            creationStatus = HttpStatus.BAD_REQUEST;
        }else {
//            Ticket ticket = dtoUtility.convertTicketDtoToTicket(ticketDto);
            response.setTicketDto(ticketDto);
            response.setStatusMsg("Ticket Booked Successfully");
            creationStatus = HttpStatus.CREATED;
        }
        return ResponseEntity
                .status(creationStatus)
                .body(response);
    }

    @PostMapping("ticket/{type}")
    public ResponseEntity<Response> bookTicket(@RequestBody TicketDto ticketDto, @PathVariable String type){
        log.info("At publish ticket Controller");

        ticketDto.setBookingTime(new Date());
        ticketDto.setType(Type.valueOf(type));
        bookingService.publishTicket(ticketDto);
        Response response = new Response();

        response.setTicketDto(ticketDto);
        response.setStatusMsg("Your Ticket will be booked shortly");
        HttpStatus creationStatus = HttpStatus.CREATED;

        return ResponseEntity
                .status(creationStatus)
                .body(response);
    }


}
