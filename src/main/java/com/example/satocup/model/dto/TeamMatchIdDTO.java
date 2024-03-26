package com.example.satocup.model.dto;

import com.example.satocup.model.dto.MatchDTO;
import com.example.satocup.model.dto.TeamDTO;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatchIdDTO implements Serializable {

    private long teamId;
    private long matchId;


}
