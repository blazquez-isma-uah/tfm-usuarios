package com.tfm.bandas.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserUpdateDTO(
        String firstName,

        String lastName,

        String secondLastName,

        @Email String email,

        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,

        Set<Long> roleIds,

        Set<Long> instrumentIds
) {}