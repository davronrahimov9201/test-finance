package test.finance.exception;

public enum ErrorItem {
    USERNAME_ALREADY_EXISTS("201","Имя пользователя уже существует"),
    USER_NOT_FOUND_BY_ID("202","Пользователь не найден по id"),
    TRANSACTION_NOT_FOUND_BY_ID("203","Транзакция не найден по id"),
    NOT_ENOUGH_BALANCE("204","Недостаточно средств на балансе"),
    ;

    private final String code;
    private final String errorMessage;

    ErrorItem(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCode() {
        return code;
    }
}
