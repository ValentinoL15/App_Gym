package com.valentino.Gym_App.dto.EjercicioDTOs;

import com.valentino.Gym_App.model.enums.CategoryGym;

public record UpdateEjercicioDTO(String name,
                                 String description,
                                 CategoryGym category) {
}
