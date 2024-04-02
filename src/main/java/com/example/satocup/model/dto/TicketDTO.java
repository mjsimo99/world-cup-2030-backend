package com.example.satocup.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long ticketId;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 4, message = "Quantity must be at most 4")
    private int quantity;

    private Long clientId;

    private Long matchId;

    @Min(value = 0, message = "Total price cannot be negative")
    private double totalPrice;

}
