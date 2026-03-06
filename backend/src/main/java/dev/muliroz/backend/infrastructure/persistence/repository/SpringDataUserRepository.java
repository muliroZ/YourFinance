package dev.muliroz.backend.infrastructure.persistence.repository;

import dev.muliroz.backend.infrastructure.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
