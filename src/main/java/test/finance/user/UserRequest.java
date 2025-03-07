package test.finance.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    @NotEmpty
    @Size(min = 3, max = 64, message = "Имя пользователя должно содержать от 3 до 64 символов")
    @Schema(description = "Имя пользователя", example = "johndoe", minLength = 3, maxLength = 64)
    private String username;

    @Size(max = 32, message = "Имя должно содержать до 32 символов")
    @Schema(description = "Имя", example = "John", nullable = true, maxLength = 32)
    private String firstName;

    @Size(max = 32, message = "Фамилия должно содержать до 32 символов")
    @Schema(description = "Фамилия", example = "Doe", nullable = true, maxLength = 32)
    private String lastName;

}
