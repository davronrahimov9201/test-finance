package test.finance.usecase.abstraction;

import test.finance.transaction.TransactionEntity;

public interface TransactionUseCase {

    TransactionEntity create(TransactionEntity model);

}
