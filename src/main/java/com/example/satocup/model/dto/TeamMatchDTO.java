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


    private Long teamId;
    private Long matchId;

    @NotBlank(message = "Team name cannot be blank")
    private String teamsName;
    @NotNull(message = "Time cannot be null")
    private LocalTime time;
    @NotNull(message = "Date cannot be null")
    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDate date;


}
