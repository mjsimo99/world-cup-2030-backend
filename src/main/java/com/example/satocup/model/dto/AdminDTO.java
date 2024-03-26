package com.example.satocup.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO extends UserDTO {
    @NotNull(message = "Admin ID cannot be blank")
    private Long adminId;
}
