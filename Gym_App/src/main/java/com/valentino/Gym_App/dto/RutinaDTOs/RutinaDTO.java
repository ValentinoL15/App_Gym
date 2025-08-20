package com.valentino.Gym_App.dto.RutinaDTOs;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.model.Ejercicio;
import com.valentino.Gym_App.model.enums.FaseGym;

public record RutinaDTO(Long rutina_id,
                        EjercicioDTO ejercicio,
                        Integer repeticiones,
                        Integer series,
                        Double peso,
                        FaseGym fase) {
}
