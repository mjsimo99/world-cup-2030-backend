package com.example.satocup.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Ticket ID cannot be null")
    private Long ticketId;

    @Column(name = "quantity")
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "clientId")
    @NotNull(message = "Client ID cannot be null")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "matchId")
    @NotNull(message = "Match ID cannot be null")
    private Match match;
}
