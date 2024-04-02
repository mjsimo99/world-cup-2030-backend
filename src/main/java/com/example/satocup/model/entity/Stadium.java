package com.example.satocup.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private Long stadiumId;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    @NotBlank(message = "Stadium name cannot be blank")
    private String name;

    @Column(name = "capacity")
    @Positive(message = "Capacity must be a positive number")
    @Min(value = 20000, message = "Capacity must be at least 20,000")
    private int capacity;

    @Column(name = "location")
    @NotBlank(message = "Location cannot be blank")
    @Size(max = 100, message = "Location must be less than or equal to 100 characters")
    private String location;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;

}
