package test.finance.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {

    @NotNull
    @Schema(description = "ID пользователя", example = "d1cb3901-2589-4a4d-b07c-5ab5c972dc3c")
    private UUID userId;

    @NotNull
    @Schema(description = "ID пользователя", example = "150")
    private BigDecimal amount;

    @NotNull
    @Schema(description = "Тип транзакции", example = "INCOME")
    private TransactionType type;

}
