package com.example.satocup.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StadiumDTO {

    @NotNull(message = "Stadium ID cannot be null")
    private Long stadiumId;

    @NotBlank(message = "Stadium name cannot be blank")
    private String name;

    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 100, message = "Location must be less than or equal to 100 characters")
    private String location;

    @NotNull(message = "City ID cannot be null")
    private Long cityId;

}
