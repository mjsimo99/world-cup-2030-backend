package com.example.satocup.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Team ID cannot be null")
    private Long teamId;

    @NotBlank(message = "Team name cannot be blank")
    @Size(max = 100, message = "Team name must be less than or equal to 100 characters")    private String name;

    @Column(name = "logo")
    @NotBlank(message = "Logo cannot be blank")
    private String logo;

    @Column(name = "country")
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @Column(name = "coach")
    @NotBlank(message = "Coach cannot be blank")
    private String coach;

    @OneToMany
    private List<Match> matches;
}
