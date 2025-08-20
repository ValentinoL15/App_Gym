package com.valentino.Gym_App.controller;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.dto.EjercicioDTOs.UpdateEjercicioDTO;
import com.valentino.Gym_App.model.Ejercicio;
import com.valentino.Gym_App.service.ServicesIntefaces.IEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {

    @Autowired
    private IEjercicioService ejercicioService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getEjercicios() {
        try {
            List<EjercicioDTO> ejercicios = ejercicioService.ejerciciosList();
            return ResponseEntity.ok(ejercicios);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEjercicioById(@PathVariable Long id) {
        try {
            EjercicioDTO ejercicio = ejercicioService.getEjercicioById(id)
                    .orElseThrow(() -> new RuntimeException("No se encuentra el usuario"));
            return ResponseEntity.ok(ejercicio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/crear-ejercicio")
    public ResponseEntity<?> crearEjercicio(@RequestBody EjercicioDTO ejercicioDTO) {
        try {
            EjercicioDTO ejercicio = ejercicioService.saveEjercicio(ejercicioDTO);
            return ResponseEntity.ok(ejercicio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/editar-ejercicio/{id}")
    public ResponseEntity<?> updateEjercicio(@PathVariable Long id,
                                             @RequestBody UpdateEjercicioDTO ejercicioDTO) {
        try {
            EjercicioDTO ejercicio = ejercicioService.updateEjercicio(id, ejercicioDTO);
            return ResponseEntity.ok(ejercicio);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/eliminar-ejercicio/{id}")
    public ResponseEntity<?> deleteEjercicio(@PathVariable Long id) {
        try {
            ejercicioService.deleteEjercicio(id);
            return ResponseEntity.ok(Map.of("message","Ejercicio eliminado con éxito"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

}
