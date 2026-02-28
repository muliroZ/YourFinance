package dev.muliroz.backend.infrastructure.persistence.specification;

import dev.muliroz.backend.domain.enums.TransactionType;
import dev.muliroz.backend.infrastructure.persistence.model.TransactionEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class TransactionSpecification {

    public static Specification<TransactionEntity> byUserId(UUID userId) {
        return (root, query, cb) -> userId == null ? null : cb.equal(root.get("userId"), userId);
    }

    public static Specification<TransactionEntity> byType(TransactionType type) {
        return (root, query, cb) -> type == null ? null : cb.equal(root.get("type"), type);
    }

    public static Specification<TransactionEntity> byDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate == null || endDate == null) {
                return null;
            }
            return cb.between(root.get("date"), startDate, endDate);
        };
    }
}
