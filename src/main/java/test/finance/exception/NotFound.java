package test.finance.exception;

import org.springframework.http.HttpStatus;

public class NotFound extends ApiException {

    private static final long serialVersionUID = 2901936588752758943L;

    public NotFound(String code, String errorMessage) {
        super(code, errorMessage, HttpStatus.NOT_FOUND);
    }

    public NotFound(String code, String errorMessage, Object data) {
        super(code, errorMessage, data, HttpStatus.NOT_FOUND);
    }
}
