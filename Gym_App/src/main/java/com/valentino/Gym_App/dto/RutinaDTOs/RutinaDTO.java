package com.valentino.Gym_App.dto.RutinaDTOs;

import com.valentino.Gym_App.model.enums.FaseGym;

public record RutinaDTO(Integer repeticiones,
                        Integer series,
                        Double peso,
                        FaseGym fase) {
}
