package com.valentino.Gym_App.controller;

import com.valentino.Gym_App.dto.SeguimientoDTOs.CreateSeguimientoDTO;
import com.valentino.Gym_App.dto.SeguimientoDTOs.SeguimientoDTO;
import com.valentino.Gym_App.service.ServicesIntefaces.ISeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seguimientos")
@PreAuthorize("isAuthenticated()")
public class SeguimientoController {

    @Autowired
    private ISeguimientoService seguimientoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeguimientos(@PathVariable Long id) {
        try {
            List<SeguimientoDTO> seguimientosList = seguimientoService.getSeguimientos(id);
            return ResponseEntity.ok(seguimientosList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/mySeg/{id}")
    public ResponseEntity<?> getSeguimiento(@PathVariable Long id) {
        try {
            SeguimientoDTO seguimientoDTO = seguimientoService.getSeguimiento(id)
                    .orElseThrow(() -> new RuntimeException("No se encuentra el seguimiento"));
            return ResponseEntity.ok(seguimientoDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",e.getMessage()));
        }
    }

    @PostMapping("/crear-seguimiento/{id}")
    public ResponseEntity<?> createSeguimiento(@PathVariable Long id,
                                               @RequestBody CreateSeguimientoDTO seguimientoDTO) {
        try {
            SeguimientoDTO seguimientoDTO1 = seguimientoService.saveSeguimiento(id,seguimientoDTO);
            return ResponseEntity.ok(seguimientoDTO1);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar-seguimiento/{id}")
    public ResponseEntity<?> deleteSeguimiento(@PathVariable Long id) {
        try {
            seguimientoService.deleteSeguimiento(id);
            return ResponseEntity.ok("Seguimiento eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/editar-seguimiento/{id}")
    public ResponseEntity<?> updateSeguimiento(@PathVariable Long id,
                                               @RequestBody SeguimientoDTO seguimientoDTO){
        try {
            SeguimientoDTO seguimientoDTO1 = seguimientoService.updateSeguimiento(id,seguimientoDTO);
            return ResponseEntity.ok(seguimientoDTO1);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

}
