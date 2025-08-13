package com.valentino.Gym_App.dto.EjercicioDTOs;

import com.valentino.Gym_App.dto.UserDTOs.UserDTO;
import com.valentino.Gym_App.model.enums.CategoryGym;
import jakarta.validation.constraints.Size;

public record EjercicioDTO(@Size(max = 20, message = "El nombre no puede contener mas de 20 caracteres")
                           String name,
                           String description,
                           CategoryGym category) {
}
