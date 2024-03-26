/*package com.example.satocup.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "matchId")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @Column(name = "quantity")
    private int quantity;
}


 */