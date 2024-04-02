package com.example.satocup.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
    private Long ticketId;

    @Column(name = "quantity")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 4, message = "Quantity must be at most 4")
    private int quantity;

    @Column(name = "totalPrice")
    @Min(value = 0, message = "Total price cannot be negative")
    private Double totalPrice;



    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;



    @ManyToOne
    @JoinColumn(name = "matchId")
    private Match match;


}
