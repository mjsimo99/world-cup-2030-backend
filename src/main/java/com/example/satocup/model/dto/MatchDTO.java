package com.example.satocup.model.dto;

import com.example.satocup.model.entity.Stadium;
import com.example.satocup.model.entity.Team;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

    @NotNull(message = "Match ID cannot be null")
    private Long matchId;

    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the future")
    private LocalDate date;

    @Column(name = "name")
    private String name;

    @NotNull(message = "Time cannot be null")
    private LocalTime time;

    @Min(value = 0, message = "Ticket price cannot be negative")
    private int ticketPrice;

    @Min(value = 0, message = "Ticket available count cannot be negative")
    private int ticketAvailable;

    @NotNull(message = "Stadium ID cannot be null")
    private Long stadiumId;

    @NotNull(message = "Team ID cannot be null")
    private Long teamId;


}
