// MatchServiceImpl.java
package com.example.satocup.service.impl;

import com.example.satocup.model.dto.MatchDTO;
import com.example.satocup.model.dto.responseDto.MatchRespDTO;
import com.example.satocup.model.entity.Match;
import com.example.satocup.repository.MatchRepository;
import com.example.satocup.service.MatchService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MatchRespDTO> getAllMatches() {
        try {
            List<Match> matches = matchRepository.findAll();
            return matches.stream()
                    .map(match -> modelMapper.map(match, MatchRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all matches: " + e.getMessage());
        }
    }

    @Override
    public MatchDTO getMatchById(Long matchId) {
        try {
            Match match = matchRepository.findById(matchId)
                    .orElseThrow(() -> new NotFoundException("Match not found with ID: " + matchId));
            return modelMapper.map(match, MatchDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch match with ID " + matchId + ": " + e.getMessage());
        }
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDTO) {
        try {
            Match match = modelMapper.map(matchDTO, Match.class);
            match = matchRepository.save(match);
            return modelMapper.map(match, MatchDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create match: " + e.getMessage());
        }
    }

    @Override
    public MatchDTO updateMatch(Long matchId, MatchDTO matchDTO) {
        try {
            Match existingMatch = matchRepository.findById(matchId)
                    .orElseThrow(() -> new NotFoundException("Match not found with ID: " + matchId));
            modelMapper.map(matchDTO, existingMatch);
            existingMatch.setMatchId(matchId);
            existingMatch.setName(matchDTO.getName());
            existingMatch = matchRepository.save(existingMatch);
            return modelMapper.map(existingMatch, MatchDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update match with ID " + matchId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteMatch(Long matchId) {
        try {
            if (!matchRepository.existsById(matchId)) {
                throw new NotFoundException("Match not found with ID: " + matchId);
            }
            matchRepository.deleteById(matchId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete match with ID " + matchId);
        }
    }
}
