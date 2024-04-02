package com.example.satocup.model.dto.responseDto;

import com.example.satocup.model.dto.ClientDTO;
import com.example.satocup.model.dto.MatchDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRespDTO {
    @NotNull(message = "Ticket ID cannot be null")
    private Long ticketId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private ClientDTO client;

    private MatchRespDTO match;

    @Min(value = 0, message = "Total price cannot be negative")
    private double totalPrice;

}
