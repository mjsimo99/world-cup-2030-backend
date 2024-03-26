// TicketServiceImpl.java
package com.example.satocup.service.impl;

import com.example.satocup.model.dto.TicketDTO;
import com.example.satocup.model.dto.responseDto.TicketRespDTO;
import com.example.satocup.model.entity.Ticket;
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

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
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
            Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
            ticket = ticketRepository.save(ticket);
            return modelMapper.map(ticket, TicketDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create ticket: " + e.getMessage());
        }
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
}
