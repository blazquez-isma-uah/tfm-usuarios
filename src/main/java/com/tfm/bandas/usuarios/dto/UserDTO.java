package com.tfm.bandas.usuarios.dto;

import java.util.Set;

// Salida: datos públicos de usuario
public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String secondLastName,
        String email,
        Set<String> roles,
        Set<String> instruments
) {}
