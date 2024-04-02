package com.example.satocup.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatchRespDTO {

    private TeamRespDTO team;
    private MatchRespDTO match;
    private LocalTime time;
    private LocalDate date;
    private String teamsName;
}
