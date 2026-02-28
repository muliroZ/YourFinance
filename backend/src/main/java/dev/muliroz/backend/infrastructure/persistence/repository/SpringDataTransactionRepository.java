package dev.muliroz.backend.infrastructure.persistence.repository;

import dev.muliroz.backend.infrastructure.persistence.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, UUID>, JpaSpecificationExecutor<TransactionEntity> {
    boolean existsByIdempotencyKey(UUID idempotencyKey);
}
