package test.finance.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import test.finance.util.ErrorMessageUtil;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(ApiException.class)
    @ApiResponses(value = {@ApiResponse(description = "Api exception")})
    public ResponseEntity<ErrorResponse> apiException(ApiException e) {
        return ResponseEntity
            .status(e.getHttpStatus())
            .body(new ErrorResponse(e.getCode(), e.getErrorMessage(), e.getData()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse("101", "Method argument not valid", errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unknownException(Exception e, ServletWebRequest webRequest) {
        String errorMessage = ErrorMessageUtil.getErrorMessage(e);
        Map<String,String> errorData = new HashMap<>() {{
            put("path",webRequest.getRequest().getRequestURI());
        }};
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("201", errorMessage, errorData));
    }
}
