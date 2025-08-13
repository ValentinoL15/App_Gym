package com.valentino.Gym_App.service.ServicesIntefaces;

import com.valentino.Gym_App.dto.SeguimientoDTOs.CreateSeguimientoDTO;
import com.valentino.Gym_App.dto.SeguimientoDTOs.SeguimientoDTO;

import java.util.List;
import java.util.Optional;

public interface ISeguimientoService {

    List<SeguimientoDTO> getSeguimientos(Long id);

    Optional<SeguimientoDTO> getSeguimiento(Long id);

    SeguimientoDTO saveSeguimiento(Long id, CreateSeguimientoDTO seguimientoDTO);

    void deleteSeguimiento(Long id);

    SeguimientoDTO updateSeguimiento(Long id, SeguimientoDTO seguimientoDTO);

}
