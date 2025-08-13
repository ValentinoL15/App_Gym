package com.valentino.Gym_App.dto.PlanDTOs;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.RutinaDTO;
import com.valentino.Gym_App.model.Seguimiento;
import com.valentino.Gym_App.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record CreatePlanDTO(Usuario user,
                            @NotNull(message = "Debes colocar una fecha de inicio") LocalDate desde,
                            @NotNull(message = "Debes colocar una fecha de finalización") LocalDate hasta,
                            @NotBlank(message = "La finalidad no puede estar vacía") String finalidad,
                            Set<RutinaDTO> rutinas,
                            Set<Seguimiento> segumiento
                            ) {
}
