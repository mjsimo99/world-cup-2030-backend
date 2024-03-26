package com.example.satocup.model.dto.responseDto;

import com.example.satocup.model.dto.StadiumDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRespDTO {
    private Long cityId;

    @NotBlank(message = "City name is required")
    @Size(max = 50, message = "City name must be less than or equal to 50 characters")
    private String name;

    private List<StadiumDTO> stadiums;

}
