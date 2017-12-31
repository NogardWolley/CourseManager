package xmu.crms.exception;
/**
 * @author zzj
 * */
public class InvalidOperationException extends Exception{
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public InvalidOperationException() {
        super();
    }

    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvalidOperationException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public InvalidOperationException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public InvalidOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOperationException(Throwable cause) {
        super(cause);
    }

    protected InvalidOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
