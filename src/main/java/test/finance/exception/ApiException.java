package test.finance.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -6399880247446892826L;

    private String code;
    private String errorMessage;
    private Object data;
    private HttpStatus httpStatus;


    public ApiException(String code, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public ApiException(String code, String errorMessage, Object data, HttpStatus httpStatus) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getData() {
        return data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
