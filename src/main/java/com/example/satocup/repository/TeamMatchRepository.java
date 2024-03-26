package com.example.satocup.repository;

import com.example.satocup.model.entity.TeamMatch;
import com.example.satocup.model.identity.TeamMatchId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMatchRepository extends JpaRepository<TeamMatch, Long> {

    Optional<TeamMatch> findById(TeamMatchId teamMatchIdDTO);
}
