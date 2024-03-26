package com.example.satocup.model.dto.responseDto;

import com.example.satocup.model.dto.TeamMatchDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRespDTO {
    @NotNull(message = "Team ID cannot be null")
    private Long teamId;

    @NotBlank(message = "Team name cannot be blank")
    @Size(max = 100, message = "Team name must be less than or equal to 100 characters")
    private String name;

    @NotBlank(message = "Logo cannot be blank")
    private String logo;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "Coach cannot be blank")
    private String coach;

    private List<TeamMatchDTO> teamMatches;


}
