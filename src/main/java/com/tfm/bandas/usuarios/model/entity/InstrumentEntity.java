package com.tfm.bandas.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "instrument",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"instrument_name", "voice"})}
)
public class InstrumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instrument_name", nullable = false)
    @NotNull
    private String instrumentName; // Ej: "Trompeta", "Clarinete"

    @Column(name = "voice", nullable = false)
    @NotNull
    private String voice; // Ej: "1", "2", "Principal"

    @ManyToMany(mappedBy = "instruments")
    @JsonIgnore
    private Set<UserProfileEntity> userProfiles = new HashSet<>();
}
