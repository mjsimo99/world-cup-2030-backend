package com.example.satocup.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatchRespDTO {

    private TeamRespDTO team;
    private MatchRespDTO match;
    private String time;
    private String date;
    private String teamsName;
}
