// MatchService.java
package com.example.satocup.service;

import com.example.satocup.model.dto.MatchDTO;
import com.example.satocup.model.dto.responseDto.MatchRespDTO;

import java.util.List;

public interface MatchService {
    List<MatchRespDTO> getAllMatches();
    MatchDTO getMatchById(Long matchId);
    MatchDTO createMatch(MatchDTO matchDTO);
    MatchDTO updateMatch(Long matchId, MatchDTO matchDTO);
    void deleteMatch(Long matchId);
}
