package com.tfm.bandas.usuarios.controller;

import com.tfm.bandas.usuarios.dto.UserCreateDTO;
import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.service.UserService;
import com.tfm.bandas.usuarios.utils.PaginatedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICIAN')")
    @GetMapping
    public PaginatedResponse<UserDTO> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        return PaginatedResponse.from(userService.getAllUsers(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICIAN')")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICIAN')")
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserCreateDTO dto) {
        return userService.createUser(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody @Valid UserCreateDTO dto) {
        return userService.updateUser(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/disable")
    public void disableUser(@PathVariable Long id) {
        userService.disableUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/enable")
    public void enableUser(@PathVariable Long id) {
        userService.enableUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/assign-instruments")
    public UserDTO assignInstruments(@PathVariable Long id, @RequestBody Set<Long> instrumentIds) {
        return userService.assignInstruments(id, instrumentIds);
    }

    // /api/users/me devuelve el perfil del usuario autenticado (extrae sub del token JWT y busca en DB con keycloakId).
    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICIAN')")
    @GetMapping("/me")
    public UserDTO getMyProfile() {
        // Extrae el IAM ID del usuario autenticado desde el contexto de seguridad
        String iamId = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByIamId(iamId);
    }

}
