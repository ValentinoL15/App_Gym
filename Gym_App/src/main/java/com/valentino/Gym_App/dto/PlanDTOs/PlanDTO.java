package com.valentino.Gym_App.dto.PlanDTOs;

import java.time.LocalDate;
import java.util.Date;

public record PlanDTO(LocalDate desde,
                      LocalDate hasta,
                      String finalidad) {
}
