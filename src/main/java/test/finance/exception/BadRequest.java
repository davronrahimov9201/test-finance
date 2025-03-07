package test.finance.exception;

import org.springframework.http.HttpStatus;

public class BadRequest extends ApiException {

    private static final long serialVersionUID = 4665936548752758191L;

    public BadRequest(String code, String errorMessage) {
        super(code, errorMessage, HttpStatus.BAD_REQUEST);
    }

    public BadRequest(String code, String errorMessage, Object data) {
        super(code, errorMessage, data, HttpStatus.BAD_REQUEST);
    }
}
