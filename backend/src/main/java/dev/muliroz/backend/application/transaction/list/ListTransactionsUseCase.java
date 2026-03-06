package dev.muliroz.backend.application.transaction.list;

import dev.muliroz.backend.domain.entity.Transaction;
import dev.muliroz.backend.domain.gateway.TransactionRepository;
import dev.muliroz.backend.domain.gateway.UserRepository;

import java.util.List;

public class ListTransactionsUseCase {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public ListTransactionsUseCase(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public ListTransactionsOutput execute(ListTransactionsInput input) {
        if (input.userId() == null || !userRepository.existsById(input.userId())) {
            throw new IllegalArgumentException("User not found");
        }

        List<Transaction> transactions = transactionRepository.search(
                input.userId(),
                input.categoryId(),
                input.type(),
                input.startDate(),
                input.endDate(),
                input.sortByAsc()
        );

        return new ListTransactionsOutput(transactions);
    }
}
