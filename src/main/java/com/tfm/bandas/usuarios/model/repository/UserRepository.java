package com.tfm.bandas.usuarios.model.repository;


import com.tfm.bandas.usuarios.model.entity.UserProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserProfileEntity, Long>,
        JpaSpecificationExecutor<UserProfileEntity> {

    Optional<UserProfileEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UserProfileEntity> findByIamId(String iamId);

    Page<UserProfileEntity> search(String firstName, String lastName, String email, Boolean active, Long instrumentId, Pageable pageable);
}