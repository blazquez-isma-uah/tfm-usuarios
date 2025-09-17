package com.tfm.bandas.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

// Entrada: creación de usuario
public record UserCreateDTO(
        @NotBlank String iamId,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String secondLastName,
        @NotBlank @Email String email,
        LocalDate birthDate,
        LocalDate bandJoinDate,
        LocalDate systemSignupDate,
        boolean active,
        String phone,
        String notes,
        String profilePictureUrl,
        Set<Long> instrumentIds
) {}

