package com.example.satocup.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Stadiums")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Stadium ID cannot be null")
    private Long stadiumId;

    @Column(name = "name")
    @NotBlank(message = "Stadium name cannot be blank")
    private String name;

    @Column(name = "capacity")
    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    @Column(name = "location")
    @NotBlank(message = "Location cannot be blank")
    @Size(max = 100, message = "Location must be less than or equal to 100 characters")
    private String location;

    @ManyToOne
    @JoinColumn(name = "cityId")
    @NotNull(message = "City ID cannot be null")
    private City city;

    @OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;

}
