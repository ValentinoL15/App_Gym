package com.valentino.Gym_App.dto.loginDTOS;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank String username,
                       @NotBlank String password) {
}
