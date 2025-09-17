package com.tfm.bandas.usuarios.model.repository;


import com.tfm.bandas.usuarios.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UserProfile> findByIamId(String iamId);
}