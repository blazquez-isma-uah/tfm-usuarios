package com.tfm.bandas.usuarios.service;

import com.tfm.bandas.usuarios.dto.UserCreateDTO;
import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.dto.UserUpdateDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDTO> getAllUsers(Pageable pageable);
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO createUser(UserCreateDTO dto);
    UserDTO updateUser(Long id, UserUpdateDTO dto);
    void deleteUser(Long id);
    void disableUser(Long id);
    void enableUser(Long id);
    UserDTO assignRoles(Long userId, Set<Long> roleIds);
    UserDTO assignInstruments(Long userId, Set<Long> instrumentIds);
}
