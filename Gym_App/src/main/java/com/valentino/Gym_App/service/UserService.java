package com.valentino.Gym_App.service;

import com.valentino.Gym_App.dto.UserDTOs.CreateUserDTO;
import com.valentino.Gym_App.dto.UserDTOs.UserDTO;
import com.valentino.Gym_App.model.Usuario;
import com.valentino.Gym_App.repository.IUserRepository;
import com.valentino.Gym_App.service.ServicesIntefaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<Usuario> userslist = userRepository.findAll();
        return userslist.stream()
                .map(user -> new UserDTO(
                        user.getUsername(),
                        user.getEmail(),
                        user.getDni(),
                        user.getAge()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUser(Long id) {

        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario"));
        return Optional.of(new UserDTO(
            user.getUsername(),
                user.getEmail(),
                user.getDni(),
                user.getAge()
        ));
    }

    @Override
    public UserDTO saveUser(CreateUserDTO user) {

        // Validaciones
        if (user.username() == null || user.username().trim().isEmpty()) {
            throw new RuntimeException("El username no puede estar vacío");
        }
        if (user.username().length() <= 3) {
            throw new RuntimeException("El username debe contener más de 3 caracteres");
        }
        if (user.dni() == null || !user.dni().matches("\\d{8}")) {
            throw new RuntimeException("El DNI debe contener 8 caracteres exactamente");
        }

        // Comprobar que no exista el username
        List<Usuario> listUsers = userRepository.findAll();
        for (Usuario usu : listUsers) {
            if (usu.getUsername().equalsIgnoreCase(user.username())) {
                throw new RuntimeException("Ya existe ese usuario, por favor elige otro nombre");
            }
        }

    Usuario usuario = new Usuario();
    usuario.setUsername(user.username());
    usuario.setDni(user.dni());
    usuario.setAge(user.age());
    usuario.setEmail(user.email());
    usuario.setPassword(encryptPassword(user.password()));
    usuario.setEjercicios(user.ejercicios() != null ? user.ejercicios() : Collections.emptySet());
    usuario.setPlanes(user.planes() != null ? user.planes() : Collections.emptySet());

    Usuario saved = userRepository.save(usuario);

    return new UserDTO(saved.getUsername(),saved.getEmail(),saved.getDni(),saved.getAge());

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        List<Usuario> listUsers = userRepository.findAll();

        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario"));

        if(userDTO.username() != null) {
            if(userDTO.username().length() <= 3) {
                throw new RuntimeException("El username no puede contener menos de 3 caracteres");
            }
            if(userDTO.username().trim().isEmpty() || userDTO.username() == null) {
                throw new RuntimeException("EL username debe contener mas de 3 caracteres");
            }
            for(Usuario usu: listUsers){
                if(userDTO.username().toUpperCase().equals(usu.getUsername().toUpperCase())){
                    throw new RuntimeException("El username ya esta en uso, cambialo por otro");
                }
            }

            user.setUsername(userDTO.username());
        }

        if(userDTO.age() != null) {
            if(userDTO.age() < 8){
                throw new RuntimeException("El usuario no puede tener menos de 8 años");
            }
            user.setAge(userDTO.age());
        }

        if(userDTO.dni() != null) {
            if(userDTO.dni().chars().allMatch(Character::isDigit)){
                throw new RuntimeException("El DNI debe contener solo números");
            }
            if(userDTO.dni() == null || userDTO.dni().length() != 8){
                throw new RuntimeException("El DNI debe contener 8 caracteres exactamente");
            }
            user.setDni(userDTO.dni());
        }

        Usuario usuario = userRepository.save(user);
        return new UserDTO(
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getDni(),
                usuario.getAge()
        );
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
