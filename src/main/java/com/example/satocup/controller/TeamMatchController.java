package com.example.satocup.controller;

import com.example.satocup.model.dto.TeamMatchDTO;
import com.example.satocup.model.dto.responseDto.TeamMatchRespDTO;
import com.example.satocup.model.entity.Match;
import com.example.satocup.model.entity.Team;
import com.example.satocup.model.identity.TeamMatchId;
import com.example.satocup.service.TeamMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/team-matches")
public class TeamMatchController {

    private final TeamMatchService teamMatchService;

    @Autowired
    public TeamMatchController(TeamMatchService teamMatchService) {
        this.teamMatchService = teamMatchService;
    }

    @GetMapping
    public ResponseEntity<List<TeamMatchRespDTO>> getAllTeamMatches() {
        List<TeamMatchRespDTO> teamMatches = teamMatchService.getAllTeamMatches();
        return ResponseEntity.ok(teamMatches);
    }

    @PostMapping
    public ResponseEntity<TeamMatchDTO> createTeamMatch(@RequestBody TeamMatchDTO teamMatchDTO) {
        TeamMatchDTO createdTeamMatch = teamMatchService.createTeamMatch(teamMatchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeamMatch);
    }
    @DeleteMapping("/{teamId}/{matchId}")
    public ResponseEntity<Void> deleteTeamMatch(@PathVariable Team teamId, @PathVariable Match matchId) {
        try {
            TeamMatchId teamMatchId = new TeamMatchId(teamId, matchId);
            teamMatchService.deleteTeamMatch(teamMatchId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        }
    @PutMapping("/{teamId}/{matchId}")
    public ResponseEntity<TeamMatchDTO> updateTeamMatch(@PathVariable Team teamId, @PathVariable Match matchId, @RequestBody TeamMatchDTO teamMatchDTO) {
        TeamMatchId teamMatchId = new TeamMatchId(teamId, matchId);
        TeamMatchDTO updatedTeamMatch = teamMatchService.updateTeamMatch(teamMatchId, teamMatchDTO);
        return ResponseEntity.ok(updatedTeamMatch);
    }


}
