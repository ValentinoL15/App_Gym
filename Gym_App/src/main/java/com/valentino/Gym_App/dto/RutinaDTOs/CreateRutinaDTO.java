package com.valentino.Gym_App.dto.RutinaDTOs;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.model.Plan;
import com.valentino.Gym_App.model.enums.FaseGym;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRutinaDTO(@NotNull EjercicioDTO ejercicio,
                              @NotBlank Integer repeticiones,
                              @NotBlank Integer series,
                              @NotBlank Double peso,
                              @NotBlank FaseGym fase,
                              @NotBlank Plan plan) {
}
