package com.example.satocup.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    @NotNull(message = "Ticket ID cannot be null")
    private Long ticketId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Client ID cannot be null")
    private Long clientId;

    @NotNull(message = "Match ID cannot be null")
    private Long matchId;
}
