package com.example.satocup.model.dto.compositionDto;

import com.example.satocup.model.dto.responseDto.MatchRespDTO;
import com.example.satocup.model.dto.responseDto.TeamRespDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatchCompRespDTO {

        private TeamRespDTO team;
        private String time;
        private String date;
        private String teamsName;

}

