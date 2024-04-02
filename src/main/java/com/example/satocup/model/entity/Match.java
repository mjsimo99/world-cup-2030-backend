package com.example.satocup.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;


    @Column(name = "ticketPrice")
    @PositiveOrZero(message = "Ticket price cannot be negative")
    @Min(value = 0, message = "Ticket price must be at least 0")
    @Max(value = 100, message = "Ticket price must be at most 100")
    private int ticketPrice;

    @Column(name = "ticketAvailable")
    @PositiveOrZero(message = "Ticket available count cannot be negative")
    @Min(value = 0, message = "Ticket available count must be at least 0")
    @Max(value = 999999, message = "Ticket available count must be at most 999999")
    private int ticketAvailable;

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "stadiumId")
    private Stadium stadium;



    @OneToMany
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    private List<TeamMatch> teamMatches;

}
