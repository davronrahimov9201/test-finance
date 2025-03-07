package test.finance.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {

    @Schema(description = "Уникальный ID", example = "484c4726-5519-43e2-8f84-1f1a9aa5da64")
    private UUID id;

    @Schema(description = "Имя пользователя", example = "johndoe", minLength = 3, maxLength = 64)
    private String username;

    @Schema(description = "Имя", example = "John", nullable = true, maxLength = 32)
    private String firstName;

    @Schema(description = "Фамилия", example = "Doe", nullable = true, maxLength = 32)
    private String lastName;

    @Schema(description = "Баланс", example = "127.85")
    private BigDecimal balance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дата создания", example = "2025-03-03 21:28:24")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дата изменения", example = "2025-03-03 21:28:24")
    private LocalDateTime modifiedDate;

}
