package com.valentino.Gym_App.repository;

import com.valentino.Gym_App.model.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeguimientoRepository extends JpaRepository<Seguimiento,Long> {
}
