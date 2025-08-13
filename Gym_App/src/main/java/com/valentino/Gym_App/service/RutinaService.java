package com.valentino.Gym_App.service;

import com.valentino.Gym_App.dto.EjercicioDTOs.EjercicioDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.CreateRutinaDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.RutinaDTO;
import com.valentino.Gym_App.dto.RutinaDTOs.UpdateRutinaDTO;
import com.valentino.Gym_App.model.Ejercicio;
import com.valentino.Gym_App.model.Plan;
import com.valentino.Gym_App.model.Rutina;
import com.valentino.Gym_App.model.Usuario;
import com.valentino.Gym_App.repository.IEjercicioRepository;
import com.valentino.Gym_App.repository.IPlanRepository;
import com.valentino.Gym_App.repository.IRutinaRepository;
import com.valentino.Gym_App.repository.IUserRepository;
import com.valentino.Gym_App.service.ServicesIntefaces.IRutinaService;
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
public class RutinaService implements IRutinaService {

    @Autowired
    private IRutinaRepository rutinaRepository;

    @Autowired
    private IEjercicioRepository ejercicioRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<RutinaDTO> getRutinas() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encuntra el usuario " + username));

        List<Rutina> rutinasList = rutinaRepository.findAll();
        Set<Rutina> myRutinas = new HashSet<>();

        for(Rutina rut: rutinasList) {
            if(rut.getPlan().getUsuario().getUser_id().equals(user.getUser_id())){
                myRutinas.add(rut);
            }
        }

        return myRutinas.stream()
                .map(rutina -> new RutinaDTO(
                        rutina.getRepeticiones(),
                        rutina.getSeries(),
                        rutina.getPeso(),
                        rutina.getFase()
                )).collect(Collectors.toList());
    }

    @Override
    public Optional<RutinaDTO> getRutina(Long id) {
        Rutina rutina = rutinaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encuentra la rutina"));
        return Optional.of(new RutinaDTO(
                rutina.getRepeticiones(),
                rutina.getSeries(),
                rutina.getPeso(),
                rutina.getFase()
        ));
    }

    @Override
    public RutinaDTO saveRutina(Long id_plan, Long id_ejercicio, CreateRutinaDTO rutinaDTO) {

        Plan plan = planRepository.findById(id_plan)
                .orElseThrow(() -> new RuntimeException("No se enceuntra el plan"));

        Ejercicio ejer = ejercicioRepository.findById(id_ejercicio)
                .orElseThrow(() -> new RuntimeException("No se encuentra el ejercicio"));

        if(rutinaDTO.repeticiones() == null) {
            throw new RuntimeException("Debes colocar repeteiciones");
        }
        if(rutinaDTO.series() == null) {
            throw new RuntimeException("Debes colocar las series que vas a hacer");
        }
        if(rutinaDTO.peso() == null) {
            throw new RuntimeException("Debes colocar el peso que vas a realizar");
        }
        Rutina rutina = new Rutina();
        rutina.setEjercicio(ejer);
        rutina.setRepeticiones(rutinaDTO.repeticiones());
        rutina.setSeries(rutinaDTO.series());
        rutina.setPeso(rutinaDTO.peso());
        rutina.setFase(rutinaDTO.fase());
        rutina.setPlan(plan);
        rutinaRepository.save(rutina);
        return new RutinaDTO(rutina.getRepeticiones(),rutina.getSeries(),rutina.getPeso(),rutina.getFase());
    }

    @Override
    public void deleteRutinaById(Long id) {
        rutinaRepository.deleteById(id);
    }

    @Override
    public RutinaDTO updateRutina(Long id, UpdateRutinaDTO rutinaDTO) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra la rutina"));
        if(rutinaDTO.repeticiones() != null) {
            if(rutinaDTO.repeticiones() == null || rutinaDTO.repeticiones() == 0) {
                throw new RuntimeException("Debes ingresar un número de repeticiones");
            }
            rutina.setRepeticiones(rutinaDTO.repeticiones());
        }
        if(rutinaDTO.series() != null) {
            if(rutinaDTO.series() == null || rutinaDTO.series() == 0){
                throw new RuntimeException("Debes ingresar un número de series");
            }
            rutina.setSeries(rutinaDTO.series());
        }
        if(rutinaDTO.peso() != null) {
            if(rutinaDTO.peso() == null){
                throw new RuntimeException("Debes ingresar un peso");
            }
            rutina.setPeso(rutinaDTO.peso());
        }
        if(rutinaDTO.fase() != null) {
            if(rutinaDTO.fase() == null) {
                throw new RuntimeException("Debes ingresar una fase");
            }
            rutina.setFase(rutinaDTO.fase());
        }

        rutinaRepository.save(rutina);
        return new RutinaDTO(rutina.getRepeticiones(),rutina.getSeries(),rutina.getPeso(),rutina.getFase());
    }
}
