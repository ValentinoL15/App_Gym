package com.valentino.Gym_App.service.ServicesIntefaces;

import com.valentino.Gym_App.dto.PlanDTOs.CreatePlanDTO;
import com.valentino.Gym_App.dto.PlanDTOs.PlanDTO;
import com.valentino.Gym_App.dto.PlanDTOs.UpdatePlanDTO;

import java.util.List;
import java.util.Optional;

public interface IPlanService {

    List<PlanDTO> getPlanes();

    Optional<PlanDTO> getPLanById(Long id);

    PlanDTO savePlan(CreatePlanDTO planDTO);

    void deletePlanById(Long id);

    PlanDTO updatePlan(Long id, UpdatePlanDTO planDTO);

}
