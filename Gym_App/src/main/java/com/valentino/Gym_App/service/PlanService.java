package com.valentino.Gym_App.service;

import com.valentino.Gym_App.dto.PlanDTOs.CreatePlanDTO;
import com.valentino.Gym_App.dto.PlanDTOs.PlanDTO;
import com.valentino.Gym_App.dto.PlanDTOs.UpdatePlanDTO;
import com.valentino.Gym_App.model.Plan;
import com.valentino.Gym_App.model.Usuario;
import com.valentino.Gym_App.repository.IPlanRepository;
import com.valentino.Gym_App.repository.IUserRepository;
import com.valentino.Gym_App.service.ServicesIntefaces.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlanService implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<PlanDTO> getPlanes() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario " + username));

        List<Plan> planesList = planRepository.findAll();
        Set<Plan> myPlanes = new HashSet<>();
        for(Plan plan: planesList){
            if(plan.getUsuario().getUser_id().equals(user.getUser_id())){
                myPlanes.add(plan);
            }
        }
        return myPlanes.stream()
                .map(plan -> new PlanDTO(
                        plan.getPlan_id(),
                        plan.getDesde(),
                        plan.getHasta(),
                        plan.getFinalidad()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlanDTO> getPLanById(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el plan"));
        return Optional.of(new PlanDTO(
                plan.getPlan_id(),
                plan.getDesde(),
                plan.getHasta(),
                plan.getFinalidad()
        ));
    }

    @Override
    public PlanDTO savePlan(CreatePlanDTO planDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario " + username));

        LocalDate horaActual = LocalDate.now();

        Plan plan = new Plan();
        plan.setUsuario(user);
        if(planDTO.desde().isBefore(horaActual)) {
            throw new RuntimeException("No puedes colocar una fecha anterior a la de hoy");
        }
        plan.setDesde(planDTO.desde());
        if(planDTO.hasta().isBefore(planDTO.desde())){
            throw new RuntimeException("Debe colocar una fecha posterior a la de su comienzo");
        }
        plan.setHasta(planDTO.hasta());
        plan.setFinalidad(planDTO.finalidad());

        planRepository.save(plan);
        return new PlanDTO(plan.getPlan_id(),plan.getDesde(),plan.getHasta(),plan.getFinalidad());

    }

    @Override
    public void deletePlanById(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public PlanDTO updatePlan(Long id, UpdatePlanDTO planDTO) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el plan"));

        if(planDTO.desde() != null) {
            if(planDTO.desde().isBefore(LocalDate.now())){
                throw new RuntimeException("No puedes colocar una fecha anterior a la de hoy");
            }
            plan.setDesde(planDTO.desde());
        }
        if(planDTO.hasta() != null) {
            if(planDTO.hasta().isBefore(planDTO.desde())){
                throw new RuntimeException("No puedes colocar una fecha anterior a la del comienzo");
            }
            plan.setHasta(planDTO.hasta());
        }
        if(planDTO.finalidad() != null) {
            if(planDTO.finalidad().trim().isEmpty()) {
                throw new RuntimeException("La finalidad no puede estar vacía");
            }
            plan.setFinalidad(planDTO.finalidad());
        }

        planRepository.save(plan);
        return new PlanDTO(plan.getPlan_id(),plan.getDesde(),plan.getHasta(),plan.getFinalidad());

    }
}
