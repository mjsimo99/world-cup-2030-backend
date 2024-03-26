package com.example.satocup.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatchDTO {


    @NotNull(message = "Team ID cannot be null")
    private Long teamId;
    @NotNull(message = "Match ID cannot be null")
    private Long matchId;
    @NotBlank(message = "Team name cannot be blank")
    private String teamsName;
    @NotBlank(message = "Time cannot be blank")
    private LocalTime time;
    @NotBlank(message = "Date cannot be blank")
    private LocalDate date;


}
