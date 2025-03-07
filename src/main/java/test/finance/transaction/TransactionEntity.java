package test.finance.transaction;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import test.finance.user.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Where(clause = "deleted_date IS NULL")
@Data
@EntityListeners(AuditingEntityListener.class)
@NamedEntityGraph(
    name = "TransactionEntity.pageable",
    attributeNodes = {
        @NamedAttributeNode("user"),
    }
)
public class TransactionEntity {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "user_id", insertable = false, updatable = false)
    private UUID userId;

}
