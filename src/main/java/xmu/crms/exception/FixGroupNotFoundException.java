package xmu.crms.exception;
/**
 * @author zzj
 * */
public class FixGroupNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public FixGroupNotFoundException() {
        super();
    }

    public FixGroupNotFoundException(String message) {
        super(message);
    }

    public FixGroupNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public FixGroupNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public FixGroupNotFoundException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public FixGroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public FixGroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FixGroupNotFoundException(Throwable cause) {
        super(cause);
    }

    protected FixGroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
