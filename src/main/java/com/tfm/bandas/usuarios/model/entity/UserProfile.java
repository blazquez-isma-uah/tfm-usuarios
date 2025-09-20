package com.tfm.bandas.usuarios.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK Interna autogenerada

    @Column(name = "iam_id", unique = true, nullable = false)
    @NotNull
    private String iamId; // ID del usuario en el sistema de autenticación externo (claims sub)

    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "second_last_name")
    private String secondLastName;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @NotNull
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "band_join_date")
    private LocalDate bandJoinDate;       // Fecha en la que se unió a la banda

    @Column(name = "system_signup_date")
    private LocalDate systemSignupDate;   // Fecha de alta en el sistema

    @Column(name = "active", nullable = false)
    @NotNull
    private boolean active;

    @Column(name = "phone")
    private String phone;

    @Column(name = "notes")
    private String notes; // Notas adicionales sobre el usuario

    @Column(name = "profile_picture_url")
    private String profilePictureUrl; // URL de la foto de perfil del usuario

    @ManyToMany
    @JoinTable(
        name = "user_profile_instrument",
        joinColumns = @JoinColumn(name = "user_profile_id"),
        inverseJoinColumns = @JoinColumn(name = "instrument_id")
    )
    private Set<Instrument> instruments = new HashSet<>();

}