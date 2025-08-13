package com.valentino.Gym_App.dto.RutinaDTOs;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.model.enums.FaseGym;
import jakarta.validation.constraints.NotBlank;

public record UpdateRutinaDTO(
                              Integer repeticiones,
                              Integer series,
                              Double peso,
                              FaseGym fase) {
}
