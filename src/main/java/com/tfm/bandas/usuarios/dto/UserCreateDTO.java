package com.tfm.bandas.usuarios.dto;

import java.util.Set;

// Entrada: creación de usuario
public record UserCreateDTO(
        String firstName,
        String lastName,
        String secondLastName,
        String email,
        String password,
        Set<Long> roleIds,
        Set<Long> instrumentIds
) {}

