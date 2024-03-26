package com.example.satocup.model.dto.responseDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRespDTO extends UserRespDto {
    @NotNull(message = "Admin ID cannot be blank")
    private Long adminId;
}
