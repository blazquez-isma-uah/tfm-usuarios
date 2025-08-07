package com.tfm.bandas.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

// Entrada: creación de usuario
public record UserCreateDTO(
        @NotBlank String firstName,

        @NotBlank String lastName,

        String secondLastName,

        @NotBlank @Email String email,

        @NotBlank
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,

        @NotEmpty(message = "Al menos un rol debe ser seleccionado")
        Set<Long> roleIds,

        Set<Long> instrumentIds
) {}

