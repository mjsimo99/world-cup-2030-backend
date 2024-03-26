package com.example.satocup.model.dto;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long cityId;

    @NotBlank(message = "City name is required")
    @Size(max = 50, message = "City name must be less than or equal to 50 characters")
    private String name;
}
