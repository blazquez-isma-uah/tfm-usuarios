package com.tfm.bandas.usuarios.model.repository;


import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.model.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Arrays;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserProfile, Long>,
        JpaSpecificationExecutor<UserProfile> {

    Optional<UserProfile> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UserProfile> findByIamId(String iamId);

    Page<UserProfile> search(String firstName, String lastName, String email, Boolean active, Long instrumentId, Pageable pageable);
}