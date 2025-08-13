package com.valentino.Gym_App.service;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.dto.EjercicioDTOs.UpdateEjercicioDTO;
import com.valentino.Gym_App.dto.UserDTOs.UserDTO;
import com.valentino.Gym_App.model.Ejercicio;
import com.valentino.Gym_App.model.Usuario;
import com.valentino.Gym_App.repository.IEjercicioRepository;
import com.valentino.Gym_App.repository.IUserRepository;
import com.valentino.Gym_App.service.ServicesIntefaces.IEjercicioService;
import com.valentino.Gym_App.service.ServicesIntefaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EjercicioService implements IEjercicioService {

    @Autowired
    private IEjercicioRepository ejercicioRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<EjercicioDTO> ejerciciosList() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encuentra el ususario " + username));

        List<Ejercicio> ejerciciosList = ejercicioRepository.findAll();

        Set<Ejercicio> myEjercicios = new HashSet<>();

        for(Ejercicio ejercicio: ejerciciosList) {
            if(ejercicio.getUsuario().getUser_id().equals(user.getUser_id())){
                myEjercicios.add(ejercicio);
            }
        }

        return myEjercicios.stream()
                .map(ejercicio -> new EjercicioDTO(
                        ejercicio.getName(),
                        ejercicio.getDescription(),
                        ejercicio.getCategory()
                )).collect(Collectors.toList());
    }

    @Override
    public Optional<EjercicioDTO> getEjercicioById(Long id) {
        Ejercicio ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario"));

        return Optional.of(new EjercicioDTO(
                ejercicio.getName(),
                ejercicio.getDescription(),
                ejercicio.getCategory()
        ));
    }

    @Override
    public EjercicioDTO saveEjercicio(EjercicioDTO ejercicioDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario " + username));


        if(ejercicioDTO.name() == null || ejercicioDTO.name().trim().isEmpty()) {
            throw new RuntimeException("No puedes ingresar un ejercicio vacio");
        }
        if(ejercicioDTO.description() == null || ejercicioDTO.description().trim().isEmpty()) {
            throw new RuntimeException("La descripción no puede ser vacía");
        }
        if(ejercicioDTO.category() == null) {
            throw new RuntimeException("La categoría no puede esatr vacía");
        }

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setName(ejercicioDTO.name());
        ejercicio.setDescription(ejercicioDTO.description());
        ejercicio.setCategory(ejercicioDTO.category());
        ejercicio.setUsuario(user);
        ejercicioRepository.save(ejercicio);

        return new EjercicioDTO(ejercicio.getName(), ejercicio.getDescription(), ejercicio.getCategory());
    }

    @Override
    public void deleteEjercicio(Long id) {
        ejercicioRepository.deleteById(id);

    }

    @Override
    public EjercicioDTO updateEjercicio(Long id, UpdateEjercicioDTO ejercicioDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario " + username));

        Ejercicio ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario"));

        if(ejercicio.getUsuario().getUser_id() != user.getUser_id()) {
            throw new RuntimeException("No tienes los permisos para editar este ejercicio");
        }

        if(ejercicioDTO.name() != null) {
            if(ejercicioDTO.name().length() < 2) {
                throw new RuntimeException("El nombre del ejercicio no puede contener menos de 2 caracteres");
            }
            if(ejercicioDTO.name() == null || ejercicioDTO.name().trim().isEmpty()) {
                throw new RuntimeException("El nombre del ejercicio no puede estar vacío");
            }
            ejercicio.setName(ejercicioDTO.name());
        }
        if(ejercicioDTO.description() != null) {
            if(ejercicioDTO.description().trim().isEmpty() || ejercicioDTO.description() == null) {
                throw new RuntimeException("La descripción no puede estar vacia");
            }
            ejercicio.setDescription(ejercicioDTO.description());
        }
        if(ejercicioDTO.category() != null) {
            ejercicio.setCategory(ejercicioDTO.category());
        }

        Ejercicio ejer = ejercicioRepository.save(ejercicio);
        return new EjercicioDTO(
                ejer.getName(),
                ejer.getDescription(),
                ejer.getCategory()
        );

    }
}
