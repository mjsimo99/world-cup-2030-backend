package com.example.satocup.service;

import com.example.satocup.model.dto.TeamMatchDTO;
import com.example.satocup.model.dto.responseDto.TeamMatchRespDTO;
import com.example.satocup.model.identity.TeamMatchId;

import java.util.List;

public interface TeamMatchService {
    List<TeamMatchRespDTO> getAllTeamMatches();
    TeamMatchDTO createTeamMatch(TeamMatchDTO teamMatchDTO);
    void deleteTeamMatch(TeamMatchId teamMatchId);
    TeamMatchDTO updateTeamMatch(TeamMatchId teamMatchId, TeamMatchDTO teamMatchDTO);
}
