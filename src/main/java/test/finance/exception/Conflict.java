package test.finance.exception;

import org.springframework.http.HttpStatus;

public class Conflict extends ApiException {

    private static final long serialVersionUID = 1255936590152758362L;

    public Conflict(String code, String errorMessage) {
        super(code, errorMessage, HttpStatus.CONFLICT);
    }

    public Conflict(String code, String errorMessage, Object data) {
        super(code, errorMessage, data, HttpStatus.CONFLICT);
    }
}
