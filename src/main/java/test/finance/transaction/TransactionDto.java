package test.finance.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.finance.user.UserDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto implements Serializable {

    @Schema(description = "Уникальный ID", example = "3a6533d1-e5b6-4050-b1bc-c5eb309eb2c1")
    private UUID id;

    @Schema(description = "ID пользователя", example = "150")
    private BigDecimal amount;

    @Schema(description = "Тип транзакции", example = "INCOME")
    private TransactionType type;

    @Schema(description = "Статус", example = "SUCCESS")
    private TransactionStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дата создания", example = "2025-03-03 21:28:24")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дата изменения", example = "2025-03-03 21:28:24")
    private LocalDateTime modifiedDate;

    @Schema(description = "Пользователь")
    private UserDto user;


}
