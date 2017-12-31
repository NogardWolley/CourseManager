package xmu.crms.exception;

/**
 * @author zzj
 * */
public class ClassesNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    public ClassesNotFoundException() {
        super();
    }

    public ClassesNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ClassesNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ClassesNotFoundException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ClassesNotFoundException(String message) {
        super(message);
    }

    public ClassesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassesNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ClassesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClassesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

