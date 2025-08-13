package com.valentino.Gym_App.dto.PlanDTOs;

import com.valentino.Gym_App.model.Rutina;
import com.valentino.Gym_App.model.Seguimiento;
import com.valentino.Gym_App.model.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record UpdatePlanDTO(Usuario user,
                            LocalDate desde,
                            LocalDate hasta,
                            String finalidad,
                            Set<Rutina> rutinas,
                            Set<Seguimiento> seguimientos) {
}
