package test.finance.specification;

import org.springframework.data.jpa.domain.Specification;
import test.finance.transaction.TransactionEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionSpecification {

    public static Specification<TransactionEntity> withFilters(UUID userId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startOfDay = startDate == null ? null : startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate == null ? null : endDate.atTime(23,59,59);
        return Specification.where(byUserId(userId))
            .and(byDateRange(startOfDay, endOfDay));
    }

    private static Specification<TransactionEntity> byUserId(UUID userId) {
        return (root, query, criteriaBuilder) ->
            userId == null ? null : criteriaBuilder.equal(root.get("userId"), userId);
    }

    private static Specification<TransactionEntity> byDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null && endDate == null) return null;
            if (startDate != null && endDate != null) {
                return criteriaBuilder.between(root.get("createdDate"), startDate, endDate);
            }
            if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), startDate);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), endDate);
        };
    }

}
