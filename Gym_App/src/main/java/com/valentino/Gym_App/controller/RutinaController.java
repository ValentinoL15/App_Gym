package com.valentino.Gym_App.controller;

import com.valentino.Gym_App.dto.RutinaDTOs.CreateRutinaDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.RutinaDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.UpdateRutinaDTO;
import com.valentino.Gym_App.model.Plan;
import com.valentino.Gym_App.model.Rutina;
import com.valentino.Gym_App.service.ServicesIntefaces.IRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    @Autowired
    private IRutinaService rutinaService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/planes-rutinas/{id}")
    public ResponseEntity<?> getRutinas(@PathVariable Long id) {
        try {
            List<RutinaDTO> rutinasList = rutinaService.getRutinas(id);
            return ResponseEntity.ok(rutinasList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRutinaById(@PathVariable Long id) {
        try {
            Optional<RutinaDTO> rutina = rutinaService.getRutina(id);
            return ResponseEntity.ok(rutina);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/crear-rutina/{id_plan}")
    public ResponseEntity<?> createRutina(@PathVariable Long id_plan,
                                          @RequestBody CreateRutinaDTO rutinaDTO) {
        try {
            RutinaDTO rutina = rutinaService.saveRutina(id_plan,rutinaDTO);
            return ResponseEntity.ok(rutina);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }

    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/eliminar-rutina/{id}")
    public ResponseEntity<?> deleteRutina(@PathVariable Long id) {
        try {
            rutinaService.deleteRutinaById(id);
            return ResponseEntity.ok(Map.of("message", "Rutina eliminada con éxito"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/editar-rutina/{id}")
    public ResponseEntity<?> updateRutina(@PathVariable Long id,
                                          @RequestBody UpdateRutinaDTO rutinaDTO) {
        try {
            RutinaDTO rutina = rutinaService.updateRutina(id,rutinaDTO);
            return ResponseEntity.ok(Map.of("message", "Rutina editada con éxito","rutina", rutina));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

}
