package com.example.satocup.service;

import com.example.satocup.model.dto.TeamDTO;
import com.example.satocup.model.dto.responseDto.TeamRespDTO;

import java.util.List;

public interface TeamService {
    List<TeamRespDTO> getAllTeams();
    TeamDTO getTeamById(Long teamId);
    TeamDTO createTeam(TeamDTO teamDTO);
    TeamDTO updateTeam(Long teamId, TeamDTO teamDTO);
    void deleteTeam(Long teamId);
}
