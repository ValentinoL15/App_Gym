package com.valentino.Gym_App.repository;

import com.valentino.Gym_App.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanRepository extends JpaRepository<Plan,Long> {
}
