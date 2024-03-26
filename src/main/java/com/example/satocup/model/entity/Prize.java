package com.example.satocup.model.entity;

import com.example.satocup.model.enumuration.TicketMatch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Prizes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prizeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TicketMatch type;

    @Column(name = "point")
    private Integer point;
}
