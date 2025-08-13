package com.valentino.Gym_App.repository;

import com.valentino.Gym_App.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRutinaRepository extends JpaRepository<Rutina,Long> {
}
