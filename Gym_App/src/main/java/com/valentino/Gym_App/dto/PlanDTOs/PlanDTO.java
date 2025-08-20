package com.valentino.Gym_App.dto.PlanDTOs;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.RutinaDTO;
import com.valentino.Gym_App.dto.SeguimientoDTOs.SeguimientoDTO;
import com.valentino.Gym_App.model.Rutina;
import com.valentino.Gym_App.model.Seguimiento;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record PlanDTO(Long plan_id,
                        LocalDate desde,
                      LocalDate hasta,
                      String finalidad) {
}
