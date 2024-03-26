package com.example.satocup.model.dto.responseDto;

import com.example.satocup.model.dto.StadiumDTO;
import com.example.satocup.model.dto.TeamDTO;
import com.example.satocup.model.dto.TeamMatchDTO;
import com.example.satocup.model.entity.TeamMatch;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRespDTO {
    @NotNull(message = "Match ID cannot be null")
    private Long matchId;

    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the future")
    private LocalDate date;

    @NotNull(message = "Time cannot be null")
    private LocalTime time;

    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Ticket price cannot be negative")
    private int ticketPrice;

    @Min(value = 0, message = "Ticket available count cannot be negative")
    private int ticketAvailable;

    @NotNull(message = "Stadium ID cannot be null")
    private StadiumDTO stadium;





}
