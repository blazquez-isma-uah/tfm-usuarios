package com.tfm.bandas.usuarios.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK Interna autogenerada

    @Column(unique = true, nullable = false)
    @NotNull
    private String iamId; // ID del usuario en el sistema de autenticación externo (claims sub)

    @Column(nullable = false)
    @NotNull
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String secondLastName;

    @Column(unique = true, nullable = false)
    @Email
    @NotNull
    private String email;

    private LocalDate birthDate;
    private LocalDate bandJoinDate;       // Fecha en la que se unió a la banda
    private LocalDate systemSignupDate;   // Fecha de alta en el sistema

    @Column(nullable = false)
    @NotNull
    private boolean active;

    private String phone;
    private String notes; // Notas adicionales sobre el usuario
    private String profilePictureUrl; // URL de la foto de perfil del usuario

    @ManyToMany
    private Set<Instrument> instruments = new HashSet<>();

}

