package com.example.satocup.model.entity;

import com.example.satocup.model.identity.TeamMatchId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "TeamMatches")
@Data
@ToString
public class TeamMatch {

    @EmbeddedId
    private TeamMatchId id;

    @Column(name = "time")
    private String time;

    @Column(name = "date")
    private String date;
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
