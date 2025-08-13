package com.valentino.Gym_App.repository;

import com.valentino.Gym_App.model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEjercicioRepository extends JpaRepository<Ejercicio,Long> {
}
