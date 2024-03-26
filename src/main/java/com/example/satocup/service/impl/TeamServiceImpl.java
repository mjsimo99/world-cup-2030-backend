// TeamServiceImpl.java
package com.example.satocup.service.impl;

import com.example.satocup.model.dto.TeamDTO;
import com.example.satocup.model.dto.responseDto.TeamRespDTO;
import com.example.satocup.model.entity.Team;
import com.example.satocup.repository.TeamRepository;
import com.example.satocup.service.TeamService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TeamRespDTO> getAllTeams() {
        try {
            List<Team> teams = teamRepository.findAll();
            return teams.stream()
                    .map(team -> modelMapper.map(team, TeamRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all teams: " + e.getMessage());
        }
    }

    @Override
    public TeamDTO getTeamById(Long teamId) {
        try {
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new NotFoundException("Team not found with ID: " + teamId));
            return modelMapper.map(team, TeamDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch team with ID " + teamId + ": " + e.getMessage());
        }
    }
@Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        try {
            Team team = modelMapper.map(teamDTO, Team.class);
            team = teamRepository.save(team);
            return modelMapper.map(team, TeamDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create team: " + e.getMessage());
        }
    }

    @Override
    public TeamDTO updateTeam(Long teamId, TeamDTO teamDTO) {
        try {
            Team existingTeam = teamRepository.findById(teamId)
                    .orElseThrow(() -> new NotFoundException("Team not found with ID: " + teamId));
            modelMapper.map(teamDTO, existingTeam);
            existingTeam.setTeamId(teamId);
            existingTeam = teamRepository.save(existingTeam);
            return modelMapper.map(existingTeam, TeamDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update team with ID " + teamId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteTeam(Long teamId) {
        try {
            if (!teamRepository.existsById(teamId)) {
                throw new NotFoundException("Team not found with ID: " + teamId);
            }
            teamRepository.deleteById(teamId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete team with ID " + teamId);
        }
    }
}
