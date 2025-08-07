package com.tfm.bandas.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleDTO(
        Long id,

        @NotBlank @Size(min = 2, max = 50)
        String name,

        @Size(max = 200)
        String description
) {}
