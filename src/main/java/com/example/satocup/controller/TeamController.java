// TeamController.java
package com.example.satocup.controller;

import com.example.satocup.model.dto.TeamDTO;
import com.example.satocup.model.dto.responseDto.TeamRespDTO;
import com.example.satocup.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamRespDTO>> getAllTeams() {
        List<TeamRespDTO> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable("id") Long teamId) {
        TeamDTO teamDTO = teamService.getTeamById(teamId);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        TeamDTO createdTeamDTO = teamService.createTeam(teamDTO);
        return new ResponseEntity<>(createdTeamDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable("id") Long teamId, @Valid @RequestBody TeamDTO teamDTO) {
        TeamDTO updatedTeamDTO = teamService.updateTeam(teamId, teamDTO);
        return new ResponseEntity<>(updatedTeamDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
