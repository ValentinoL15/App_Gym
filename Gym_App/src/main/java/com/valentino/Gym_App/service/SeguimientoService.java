package com.valentino.Gym_App.service;

import com.valentino.Gym_App.dto.PlanDTOs.PlanDTO;
import com.valentino.Gym_App.dto.SeguimientoDTOs.CreateSeguimientoDTO;
import com.valentino.Gym_App.dto.SeguimientoDTOs.SeguimientoDTO;
import com.valentino.Gym_App.model.Plan;
import com.valentino.Gym_App.model.Seguimiento;
import com.valentino.Gym_App.model.Usuario;
import com.valentino.Gym_App.repository.IPlanRepository;
import com.valentino.Gym_App.repository.ISeguimientoRepository;
import com.valentino.Gym_App.repository.IUserRepository;
import com.valentino.Gym_App.service.ServicesIntefaces.IPlanService;
import com.valentino.Gym_App.service.ServicesIntefaces.ISeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeguimientoService implements ISeguimientoService {

    @Autowired
    private ISeguimientoRepository seguimientoRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public List<SeguimientoDTO> getSeguimientos(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encuentra el user " + username));
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el plan"));

        List<Seguimiento> seguimientosList = seguimientoRepository.findAll();
        Set<Seguimiento> mySeguimientos = new HashSet<>();

        for (Seguimiento seg : seguimientosList) {
            if (seg.getPlan().getPlan_id().equals(plan.getPlan_id()) && plan.getUsuario().getUser_id().equals(user.getUser_id())) {
                mySeguimientos.add(seg);
            }
        }

        return mySeguimientos.stream()
                .map(segui -> new SeguimientoDTO(
                        segui.getFecha(),
                        segui.getDescription()
                )).collect(Collectors.toList());
    }

    @Override
    public Optional<SeguimientoDTO> getSeguimiento(Long id) {
        Seguimiento seguimiento = seguimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el seguimiento"));
        return Optional.of(new SeguimientoDTO(
                seguimiento.getFecha(),
                seguimiento.getDescription()
        ));
    }

    @Override
    public SeguimientoDTO saveSeguimiento(Long id, CreateSeguimientoDTO seguimientoDTO) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el plan"));
        if (seguimientoDTO.fecha().isBefore(plan.getDesde())) {
            throw new RuntimeException("No puedes colocar una fecha de seguimiento anterior al comienzo del plan");
        }
        if(seguimientoDTO.fecha().isAfter(plan.getHasta())){
            throw new RuntimeException("No puedes colocar una fecha de seguimiento posterior a la fecha de finalización del plan");
        }
        if (seguimientoDTO.description() == null || seguimientoDTO.description().trim().isEmpty()) {
            throw new RuntimeException("No puedes colocar una descripción vacía");
        }
        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setFecha(seguimientoDTO.fecha());
        seguimiento.setDescription(seguimientoDTO.description());
        seguimiento.setPlan(plan);
        seguimientoRepository.save(seguimiento);

        return new SeguimientoDTO(
                seguimiento.getFecha(),
                seguimiento.getDescription()
        );
    }

    @Override
    public void deleteSeguimiento(Long id) {
        seguimientoRepository.deleteById(id);
    }

    @Override
    public SeguimientoDTO updateSeguimiento(Long id, SeguimientoDTO seguimientoDTO) {
        Seguimiento seguimiento = seguimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el seguimiento"));
        Plan plan = seguimiento.getPlan();
        if (seguimientoDTO.fecha() != null) {
            if (seguimientoDTO.fecha().isBefore(plan.getDesde())) {
                throw new RuntimeException("No puedes colocar una fecha de seguimiento anterior al comienzo del plan");
            }
            if(seguimientoDTO.fecha().isAfter(plan.getHasta())){
                throw new RuntimeException("No puedes colocar una fecha de seguimiento posterior a la fecha de finalización del plan");
            }
            seguimiento.setFecha(seguimientoDTO.fecha());
        }
        if (seguimientoDTO.description() != null) {
            if (seguimientoDTO.description().trim().isEmpty()) {
                throw new RuntimeException("La descripción no puede estar vacía");
            }
            seguimiento.setDescription(seguimientoDTO.description());
        }
        seguimientoRepository.save(seguimiento);
        return new SeguimientoDTO(
                seguimiento.getFecha(),
                seguimiento.getDescription()
        );
    }
}