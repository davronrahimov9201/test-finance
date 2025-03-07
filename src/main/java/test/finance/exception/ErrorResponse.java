package test.finance.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse implements Serializable {

    @Schema(description = "Код", example = "101")
    private String code;

    @Schema(description = "Сообщение об ошибке", example = "Имя пользователя уже существует")
    private String errorMessage;

    @Schema(description = "Данные об ошибке", example = "{\"username\": \"john\"}", nullable = true)
    protected Object data;

}
