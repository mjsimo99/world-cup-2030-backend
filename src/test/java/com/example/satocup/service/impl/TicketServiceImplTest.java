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
    @Test
    void getTicketFail() {
        List<Ticket> tickets = Collections.singletonList(ticket);

        when(ticketRepository.findAll()).thenReturn(tickets);
        when(modelMapper.map(ticket, TicketRespDTO.class)).thenReturn(new TicketRespDTO());

        List<TicketRespDTO> result = ticketService.getAllTickets();

        assertFalse(result.isEmpty());
        verify(ticketRepository).findAll();
        verify(modelMapper).map(ticket, TicketRespDTO.class);
    }
    @Test
    void getTicketById() {
        when(ticketRepository.findById(1L)).thenReturn(java.util.Optional.of(ticket));
        when(modelMapper.map(ticket, TicketDTO.class)).thenReturn(ticketDTO);

        TicketDTO result = ticketService.getTicketById(1L);

        assertEquals(ticketDTO, result);
        verify(ticketRepository).findById(1L);
        verify(modelMapper).map(ticket, TicketDTO.class);
    }


    @Test
    void createTicket() {
        Match match = new Match();
        match.setMatchId(1L);
        match.setTicketAvailable(10);
        Client client = new Client();
        client.setClientId(1L);
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setMatchId(1L);
        ticketDTO.setClientId(1L);
        ticketDTO.setQuantity(2);

        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(modelMapper.map(ticketDTO, Ticket.class)).thenReturn(ticket);
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(modelMapper.map(ticket, TicketDTO.class)).thenReturn(ticketDTO);

        TicketDTO result = ticketService.createTicket(ticketDTO);

        assertEquals(ticketDTO, result);
        verify(matchRepository).findById(1L);
        verify(clientRepository).findById(1L);
        verify(modelMapper).map(ticketDTO, Ticket.class);
        verify(ticketRepository).save(ticket);
        verify(modelMapper).map(ticket, TicketDTO.class);

    }

}