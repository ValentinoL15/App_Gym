package com.valentino.Gym_App.service.ServicesIntefaces;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.dto.EjercicioDTOs.UpdateEjercicioDTO;
import com.valentino.Gym_App.model.Ejercicio;

import java.util.List;
import java.util.Optional;

public interface IEjercicioService {

    List<EjercicioDTO> ejerciciosList();

    Optional<EjercicioDTO> getEjercicioById(Long id);

    EjercicioDTO saveEjercicio(EjercicioDTO ejercicioDTO);

    void deleteEjercicio(Long id);

    EjercicioDTO updateEjercicio(Long id, UpdateEjercicioDTO ejercicioDTO);

}
