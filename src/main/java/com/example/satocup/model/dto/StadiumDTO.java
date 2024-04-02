package com.example.satocup.model.dto;


import com.example.satocup.model.entity.City;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StadiumDTO {

    private Long stadiumId;

    @NotBlank(message = "Stadium name cannot be blank")
    private String name;

    @Positive(message = "Capacity must be a positive number")
    @Min(value = 20000, message = "Capacity must be at least 20,000")
    private int capacity;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 100, message = "Location must be less than or equal to 100 characters")
    private String location;

    private Long cityId;


}
