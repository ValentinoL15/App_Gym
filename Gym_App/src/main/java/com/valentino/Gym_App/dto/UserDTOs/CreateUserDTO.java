package com.valentino.Gym_App.dto.UserDTOs;

import com.valentino.Gym_App.model.Ejercicio;
import com.valentino.Gym_App.model.Plan;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateUserDTO(@NotBlank String username,
                            @NotBlank String password,
                            @NotBlank String email,
                            @NotBlank String dni,
                            @NotNull @Min(8) Integer age,
                            Set<Ejercicio> ejercicios,
                            Set<Plan> planes) {
}
