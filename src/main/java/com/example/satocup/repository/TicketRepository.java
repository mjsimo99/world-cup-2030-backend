package com.example.satocup.repository;

import com.example.satocup.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByClient_ClientId(Long clientId);
    List<Ticket> findByClient_ClientIdAndMatch_MatchId(Long clientId, Long matchId);


}
