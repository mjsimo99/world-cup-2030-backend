package com.example.satocup.model.dto.responseDto;

import com.example.satocup.model.dto.TicketDTO;
import com.example.satocup.model.entity.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRespDTO {
    @NotNull(message = "Client ID cannot be blank")
    private Long clientId;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 100, message = "Address must be less than or equal to 100 characters")
    private String address;

    @NotNull(message = "Money cannot be null")
    private double money;

    private List<TicketDTO> tickets;
}
