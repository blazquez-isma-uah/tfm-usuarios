package com.tfm.bandas.usuarios.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String firstName;
    private String lastName;
    private String secondLastName;

    @Column(unique = true, nullable = false)
    @Email
    @NotNull
    private String email;

    private String passwordHash;

    @Column(nullable = false)
    @NotNull
    private LocalDate birthDate;
    @Column(nullable = false)
    @NotNull
    private LocalDate bandJoinDate;       // Fecha en la que se unió a la banda
    private LocalDate systemSignupDate;   // Fecha de alta en el sistema
    private LocalDateTime lastLoginDate;  // Fecha del último inicio de sesión

    @Column(nullable = false)
    @NotNull
    private boolean active;

    private String phone;
    private String notes; // Notas adicionales sobre el usuario
    private String profilePictureUrl; // URL de la foto de perfil del usuario

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    private Set<Instrument> instruments = new HashSet<>();

}

