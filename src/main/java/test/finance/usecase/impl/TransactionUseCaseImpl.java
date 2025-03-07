package test.finance.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import test.finance.exception.Conflict;
import test.finance.exception.ErrorItem;
import test.finance.exception.ErrorKey;
import test.finance.service.abstraction.TransactionService;
import test.finance.service.abstraction.UserService;
import test.finance.transaction.TransactionEntity;
import test.finance.transaction.TransactionStatus;
import test.finance.transaction.TransactionType;
import test.finance.usecase.abstraction.TransactionUseCase;
import test.finance.user.UserEntity;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class TransactionUseCaseImpl implements TransactionUseCase {

    private final UserService userService;
    private final TransactionService transactionService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransactionEntity create(TransactionEntity model) {
        UserEntity user = userService.getById(model.getUserId());
        model.setUser(user);
        model.setStatus(TransactionStatus.SUCCESS); // TODO
        if(model.getType().equals(TransactionType.OUTCOME)) {
            checkBalance(model.getAmount(), user.getBalance());
            user.setBalance(user.getBalance().subtract(model.getAmount()));
        } else {
            user.setBalance(user.getBalance().add(model.getAmount()));
        }
        userService.save(user);
        return transactionService.create(model);
    }

    private void checkBalance(BigDecimal amount, BigDecimal balance) {
        if(amount.compareTo(balance) > 0) {
            ErrorItem error = ErrorItem.NOT_ENOUGH_BALANCE;
            throw new Conflict(
                error.getCode(),
                error.getErrorMessage(),
                new HashMap<>() {{
                    put(ErrorKey.BALANCE, balance);
                    put(ErrorKey.AMOUNT, amount);
                }}
            );
        }
    }
}
