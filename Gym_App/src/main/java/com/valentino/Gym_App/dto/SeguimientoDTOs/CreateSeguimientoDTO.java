package com.valentino.Gym_App.dto.SeguimientoDTOs;

import com.valentino.Gym_App.dto.PlanDTOs.PlanDTO;

import java.time.LocalDate;

public record CreateSeguimientoDTO(LocalDate fecha,
                                   String description,
                                   PlanDTO plan) {
}
