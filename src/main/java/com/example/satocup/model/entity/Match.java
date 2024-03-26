package com.example.satocup.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Match ID cannot be null")
    private Long matchId;


    @Column(name = "ticketPrice")
    @Min(value = 0, message = "Ticket price cannot be negative")
    private int ticketPrice;

    @Column(name = "ticketAvailable")
    @Min(value = 0, message = "Ticket available count cannot be negative")
    private int ticketAvailable;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "stadiumId")
    @NotNull(message = "Stadium ID cannot be null")
    private Stadium stadium;



    @OneToMany
    private List<Ticket> tickets;

    @OneToMany
    private List<TeamMatch> teamMatches;

}
