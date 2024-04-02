// TicketServiceImpl.java
package com.example.satocup.service.impl;

import com.example.satocup.model.dto.TicketDTO;
import com.example.satocup.model.dto.responseDto.TicketRespDTO;
import com.example.satocup.model.entity.Client;
import com.example.satocup.model.entity.Match;
import com.example.satocup.model.entity.Ticket;
import com.example.satocup.repository.ClientRepository;
import com.example.satocup.repository.MatchRepository;
import com.example.satocup.repository.TicketRepository;
import com.example.satocup.service.TicketService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;
    private final MatchRepository matchRepository;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, ClientRepository clientRepository, MatchRepository matchRepository) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<TicketRespDTO> getAllTickets() {
        try {
            List<Ticket> tickets = ticketRepository.findAll();
            return tickets.stream()
                    .map(ticket -> modelMapper.map(ticket, TicketRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all tickets: " + e.getMessage());
        }
    }

    @Override
    public TicketDTO getTicketById(Long ticketId) {
        try {
            Ticket ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new NotFoundException("Ticket not found with ID: " + ticketId));
            return modelMapper.map(ticket, TicketDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch ticket with ID " + ticketId + ": " + e.getMessage());
        }
    }

@Override
public TicketDTO createTicket(TicketDTO ticketDTO) {
    try {
        Long clientId = ticketDTO.getClientId();
        Long matchId = ticketDTO.getMatchId();
        int quantity = ticketDTO.getQuantity();

        List<Ticket> existingTickets = ticketRepository.findByClient_ClientIdAndMatch_MatchId(clientId, matchId);
        if (!existingTickets.isEmpty()) {
            throw new IllegalArgumentException("You already reserved a tickets for this match");
        }

        double totalPrice = calcul(matchId, clientId, quantity);

        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        ticket.setQuantity(quantity);
        ticket.setTotalPrice(totalPrice);

        ticket = ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketDTO.class);
    } catch (Exception e) {
        throw new RuntimeException("Failed to create ticket: " + e.getMessage());
    }
}

    private double calcul(Long matchId, Long clientId, int quantity) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        if (match.getTicketAvailable() < quantity) {
            throw new IllegalArgumentException("Not enough tickets available");
        }

        double totalPrice = match.getTicketPrice() * quantity;

        if (client.getMoney() < totalPrice) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        match.setTicketAvailable(match.getTicketAvailable() - quantity);
        client.setMoney(client.getMoney() - totalPrice);

        return totalPrice;
    }
    @Override
    public TicketDTO updateTicket(Long ticketId, TicketDTO ticketDTO) {
        try {
            Ticket existingTicket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new NotFoundException("Ticket not found with ID: " + ticketId));
            modelMapper.map(ticketDTO, existingTicket);
            existingTicket.setTicketId(ticketId);
            existingTicket = ticketRepository.save(existingTicket);
            return modelMapper.map(existingTicket, TicketDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update ticket with ID " + ticketId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteTicket(Long ticketId) {
        try {
            if (!ticketRepository.existsById(ticketId)) {
                throw new NotFoundException("Ticket not found with ID: " + ticketId);
            }
            ticketRepository.deleteById(ticketId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete ticket with ID " + ticketId);
        }
    }
    @Override
    public List<TicketRespDTO> getTicketsByClientId(Long clientId) {
        try {
            List<Ticket> tickets = ticketRepository.findByClient_ClientId(clientId);
            return tickets.stream()
                    .map(ticket -> modelMapper.map(ticket, TicketRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch tickets for client with ID " + clientId + ": " + e.getMessage());
        }
    }


}
