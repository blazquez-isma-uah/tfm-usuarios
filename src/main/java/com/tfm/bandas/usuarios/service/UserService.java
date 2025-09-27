package com.tfm.bandas.usuarios.service;

import com.tfm.bandas.usuarios.dto.UserCreateDTO;
import com.tfm.bandas.usuarios.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserService {
    Page<UserDTO> getAllUsers(Pageable pageable);
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO getUserByUsername(String username);
    UserDTO getUserByIamId(String iamId);
    UserDTO createUser(UserCreateDTO dto);
    UserDTO updateUser(Long id, UserCreateDTO dto);
    void deleteUser(Long id);
    void disableUser(Long id);
    void enableUser(Long id);
    UserDTO updateUserInstruments(Long userId, Set<Long> instrumentIds);
    Page<UserDTO> searchUsers(String username, String firstName, String lastName, String email, Boolean active, Long instrumentId, Pageable pageable);
}
