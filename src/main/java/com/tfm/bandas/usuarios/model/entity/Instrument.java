package com.tfm.bandas.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"instrumentName", "voice"})})
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String instrumentName; // Ej: "Trompeta", "Clarinete"
    @Column(nullable = false)
    @NotNull
    private String voice; // Ej: "1", "2", "Principal"

    @ManyToMany(mappedBy = "instruments")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Instrument() {
    }

    public Instrument(String instrumentName, String voice) {
        this.instrumentName = instrumentName;
        this.voice = voice;
    }

}
