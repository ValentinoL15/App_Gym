package com.valentino.Gym_App.service.ServicesIntefaces;

import com.valentino.Gym_App.dto.UserDTOs.CreateUserDTO;
import com.valentino.Gym_App.dto.UserDTOs.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUser(Long id);

    UserDTO saveUser(CreateUserDTO user);

    void deleteUser(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    public String encryptPassword(String password);



}
