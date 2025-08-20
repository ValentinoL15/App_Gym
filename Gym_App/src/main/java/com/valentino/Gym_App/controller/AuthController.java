package com.valentino.Gym_App.controller;

import com.valentino.Gym_App.dto.UserDTOs.CreateUserDTO;
import com.valentino.Gym_App.dto.UserDTOs.UserDTO;
import com.valentino.Gym_App.dto.loginDTOS.AuthResponseDTO;
import com.valentino.Gym_App.dto.loginDTOS.LoginDTO;
import com.valentino.Gym_App.model.Usuario;
import com.valentino.Gym_App.repository.IUserRepository;
import com.valentino.Gym_App.service.ServicesIntefaces.IUserService;
import com.valentino.Gym_App.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO userDTO) {
        try {
            UserDTO usuario = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Ocurrió un error inesperado"));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO login) {
        try {
            AuthResponseDTO response = userDetailsService.loginUser(login);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuario no encontrado"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Contraseña incorrecta"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error interno"));
        }
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UserDTO userDTO) {
        try {
            UserDTO user = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", e.getMessage()));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(){
        try {
            UserDTO user = userService.getUser()
                    .orElseThrow(() -> new RuntimeException("No se encuentra el usuario"));
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }


}
