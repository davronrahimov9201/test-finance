package test.finance.service.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import test.finance.transaction.TransactionEntity;

import java.time.LocalDate;
import java.util.UUID;

public interface TransactionService {

    TransactionEntity create(TransactionEntity model);
    TransactionEntity getById(UUID id);
    void delete(UUID id);
    Page<TransactionEntity> findAll(Pageable pageable);
    void refresh(TransactionEntity model);
    Page<TransactionEntity> getTransactions(UUID userId, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
