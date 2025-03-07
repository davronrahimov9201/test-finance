package test.finance.util;
public class ErrorMessageUtil {
    public static String getErrorMessage(Throwable e) {
        return e.getMessage() == null ? "error message is empty!" : (e.getMessage().length() > 512 ? e.getMessage().substring(0,512) : e.getMessage());
    }
}
