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

    private Long matchId;


    @Column(name = "name")
    private String name;

    @PositiveOrZero(message = "Ticket price cannot be negative")
    @Min(value = 0, message = "Ticket price must be at least 0")
    @Max(value = 100, message = "Ticket price must be at most 100")
    private int ticketPrice;

    @PositiveOrZero(message = "Ticket available count cannot be negative")
    @Min(value = 0, message = "Ticket available count must be at least 0")
    @Max(value = 999999, message = "Ticket available count must be at most 999999")
    private int ticketAvailable;

    private Long stadiumId;

    private Long teamId;


}
