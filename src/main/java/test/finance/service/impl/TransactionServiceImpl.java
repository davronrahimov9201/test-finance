package test.finance.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.finance.exception.ErrorItem;
import test.finance.exception.ErrorKey;
import test.finance.exception.NotFound;
import test.finance.service.abstraction.TransactionService;
import test.finance.specification.TransactionSpecification;
import test.finance.transaction.TransactionEntity;
import test.finance.transaction.TransactionRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final EntityManager entityManager;

    @Override
    public TransactionEntity create(TransactionEntity model) {
        return repository.save(model);
    }

    @Override
    public TransactionEntity getById(UUID id) {
        Optional<TransactionEntity> optional = repository.findById(id);
        if(optional.isEmpty()) {
            ErrorItem error = ErrorItem.TRANSACTION_NOT_FOUND_BY_ID;
            throw new NotFound(
                error.getCode(),
                error.getErrorMessage(),
                new HashMap<>() {{ put(ErrorKey.ID, id); }}
            );
        }
        return optional.get();
    }

    @Override
    public void delete(UUID id) {
        repository.delete(getById(id));
    }

    @Override
    public Page<TransactionEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public void refresh(TransactionEntity model) {
        entityManager.refresh(entityManager.merge(model));
    }

    @Override
    public Page<TransactionEntity> getTransactions(UUID userId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Specification<TransactionEntity> specification = TransactionSpecification.withFilters(userId, startDate, endDate);
        return repository.findAll(specification, pageable);
    }

}
