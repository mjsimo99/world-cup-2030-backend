package com.example.satocup.model.entity;

import com.example.satocup.model.identity.TeamMatchId;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "TeamMatches")
@Data
@ToString
public class TeamMatch {

    @EmbeddedId
    private TeamMatchId id;

    @Column(name = "time")
    @NotNull(message = "Time cannot be null")
    private LocalTime time;

    @Column(name = "date")
    @FutureOrPresent(message = "Date must be in the future or present")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;
    @Column(name = "teams_name")
    @NotBlank(message = "Team name cannot be blank")
    private String teamsName;

    @MapsId("team")
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @MapsId("match")
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;


}
