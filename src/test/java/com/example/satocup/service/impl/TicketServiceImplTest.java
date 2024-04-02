package com.example.satocup.service.impl;

import com.example.satocup.model.dto.TicketDTO;
import com.example.satocup.model.dto.responseDto.TicketRespDTO;
import com.example.satocup.model.entity.Client;
import com.example.satocup.model.entity.Match;
import com.example.satocup.model.entity.Stadium;
import com.example.satocup.model.entity.Ticket;
import com.example.satocup.repository.ClientRepository;
import com.example.satocup.repository.MatchRepository;
import com.example.satocup.repository.TicketRepository;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;
    private Match match;
    private Client client;

    private TicketDTO ticketDTO;

    @BeforeEach
    void setUp() {
        ticket = new Ticket(1L, 2, 10.00, new Client(), new Match());
        ticket.setTicketId(1L);




        ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(1L);
    }

    @Test
    void getAllTickets() {
        List<Ticket> tickets = Collections.singletonList(ticket);
        List<TicketRespDTO> ticketRespDTOs = Collections.singletonList(new TicketRespDTO());

        when(ticketRepository.findAll()).thenReturn(tickets);
        when(modelMapper.map(ticket, TicketRespDTO.class)).thenReturn(new TicketRespDTO());


        List<TicketRespDTO> result = ticketService.getAllTickets();

        assertEquals(ticketRespDTOs.size(), result.size());
        verify(ticketRepository).findAll();
        verify(modelMapper).map(ticket, TicketRespDTO.class);
    }

}