package dev.muliroz.backend.infrastructure.persistence.repository;

import dev.muliroz.backend.domain.entity.Transaction;
import dev.muliroz.backend.domain.enums.TransactionType;
import dev.muliroz.backend.domain.gateway.TransactionRepository;
import dev.muliroz.backend.infrastructure.persistence.model.TransactionEntity;
import dev.muliroz.backend.infrastructure.persistence.specification.TransactionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final SpringDataTransactionRepository repository;

    public JpaTransactionRepository(SpringDataTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getCategoryId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDate(),
                transaction.getDescription(),
                transaction.getIdempotencyKey(),
                transaction.getCreatedAt()
        );

        repository.save(transactionEntity);
    }

    @Override
    public boolean existsByIdempotencyKey(UUID idempotencyKey) {
        return repository.existsByIdempotencyKey(idempotencyKey);
    }

    @Override
    public List<Transaction> search(UUID userId, UUID categoryId, TransactionType type, LocalDate startDate, LocalDate endDate, Boolean sortByAsc) {
        Specification<TransactionEntity> spec = Specification.where(TransactionSpecification.byUserId(userId))
                .and(TransactionSpecification.byCategoryId(categoryId))
                .and(TransactionSpecification.byType(type))
                .and(TransactionSpecification.byDateBetween(startDate, endDate));

        Sort sort = Sort.unsorted();

        if (sortByAsc != null) {
            sort = sortByAsc
                    ? Sort.by(Sort.Direction.ASC, "date")
                    : Sort.by(Sort.Direction.DESC, "date");
        }

        List<TransactionEntity> entityList = repository.findAll(spec, sort);

        return entityList.stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }
}
