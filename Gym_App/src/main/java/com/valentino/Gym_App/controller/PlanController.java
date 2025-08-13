package com.valentino.Gym_App.controller;

import com.valentino.Gym_App.dto.PlanDTOs.CreatePlanDTO;
import com.valentino.Gym_App.dto.PlanDTOs.PlanDTO;
import com.valentino.Gym_App.dto.PlanDTOs.UpdatePlanDTO;
import com.valentino.Gym_App.model.Plan;
import com.valentino.Gym_App.service.ServicesIntefaces.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planes")
public class PlanController {

    @Autowired
    private IPlanService planService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getPlanes() {
        try {
            List<PlanDTO> planesList = planService.getPlanes();
            return ResponseEntity.ok(planesList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanById(@PathVariable Long id) {
        try {
            PlanDTO plan = planService.getPLanById(id)
                    .orElseThrow(() -> new RuntimeException("No se encuentra el plan"));
            return ResponseEntity.ok(plan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/crear-plan")
    public ResponseEntity<?> createPlan(@RequestBody CreatePlanDTO planDTO) {
        try {
            PlanDTO plan = planService.savePlan(planDTO);
            return ResponseEntity.ok(plan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/eliminar-plan/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        try {
            planService.deletePlanById(id);
            return ResponseEntity.ok("Plan eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/edit-plan/{id}")
    public ResponseEntity<?> updatePlan(@PathVariable Long id,
                                        @RequestBody UpdatePlanDTO planDTO) {
        try {
            PlanDTO plan = planService.updatePlan(id,planDTO);
            return ResponseEntity.ok(plan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

}
