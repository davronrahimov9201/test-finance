package test.finance.transaction;

import org.springframework.stereotype.Component;
import test.finance.generic.Converter;
import test.finance.user.UserConverter;

@Component
public class TransactionConverter extends Converter<TransactionDto, TransactionEntity, TransactionRequest> {

    public TransactionConverter(UserConverter userConverter) {
        super((request, entity) -> {
            entity.setUserId(request.getUserId());
            entity.setAmount(request.getAmount());
            entity.setType(request.getType());
            return entity;
        }, entity -> new TransactionDto(
            entity.getId(),
            entity.getAmount(),
            entity.getType(),
            entity.getStatus(),
            entity.getCreatedDate(),
            entity.getModifiedDate(),
            userConverter.toDto(entity.getUser())
        ));
    }
}
