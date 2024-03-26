package com.example.satocup.model.dto.responseDto;

import com.example.satocup.model.dto.CityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StadiumRespDTO {
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
    private CityDTO city;

    //private List<MatchRespDTO> matches;

}
