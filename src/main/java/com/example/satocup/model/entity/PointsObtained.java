package com.example.satocup.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Points_Obtained")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsObtained {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pointId;

    @Column(name = "ticketNumber")
    private Integer ticketNumber;

    @Column(name = "points")
    private Integer points;
}
