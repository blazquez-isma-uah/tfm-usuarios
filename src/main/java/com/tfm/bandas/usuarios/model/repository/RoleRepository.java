package com.tfm.bandas.usuarios.model.repository;

import com.tfm.bandas.usuarios.model.entity.Role;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(@NotNull String name);
}
