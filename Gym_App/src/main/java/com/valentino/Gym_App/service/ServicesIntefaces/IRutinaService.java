package com.valentino.Gym_App.service.ServicesIntefaces;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.CreateRutinaDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.RutinaDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.UpdateRutinaDTO;
import com.valentino.Gym_App.model.Ejercicio;

import java.util.List;
import java.util.Optional;

public interface IRutinaService {

    List<RutinaDTO> getRutinas(Long id);

    Optional<RutinaDTO> getRutina(Long id);

    RutinaDTO saveRutina(Long id_plan,CreateRutinaDTO rutinaDTO);

    void deleteRutinaById(Long id);

    RutinaDTO updateRutina(Long id, UpdateRutinaDTO rutinaDTO);

}
