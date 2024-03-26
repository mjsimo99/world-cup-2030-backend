// TicketService.java
package com.example.satocup.service;

import com.example.satocup.model.dto.TicketDTO;
import com.example.satocup.model.dto.responseDto.TicketRespDTO;

import java.util.List;

public interface TicketService {
    List<TicketRespDTO> getAllTickets();
    TicketDTO getTicketById(Long ticketId);
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO updateTicket(Long ticketId, TicketDTO ticketDTO);
    void deleteTicket(Long ticketId);
}
