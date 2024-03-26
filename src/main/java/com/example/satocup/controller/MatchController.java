// MatchController.java
package com.example.satocup.controller;

import com.example.satocup.model.dto.MatchDTO;
import com.example.satocup.model.dto.responseDto.MatchRespDTO;
import com.example.satocup.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchRespDTO>> getAllMatches() {
        List<MatchRespDTO> matches = matchService.getAllMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable("id") Long matchId) {
        MatchDTO matchDTO = matchService.getMatchById(matchId);
        return new ResponseEntity<>(matchDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@Valid @RequestBody MatchDTO matchDTO) {
        MatchDTO createdMatchDTO = matchService.createMatch(matchDTO);
        return new ResponseEntity<>(createdMatchDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable("id") Long matchId, @Valid @RequestBody MatchDTO matchDTO) {
        MatchDTO updatedMatchDTO = matchService.updateMatch(matchId, matchDTO);
        return new ResponseEntity<>(updatedMatchDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable("id") Long matchId) {
        matchService.deleteMatch(matchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
