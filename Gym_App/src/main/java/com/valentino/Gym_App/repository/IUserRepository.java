package com.valentino.Gym_App.repository;

import com.valentino.Gym_App.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUserEntityByUsername(String username);

    Optional<Usuario> findUserEntityByEmail(String email);

}
