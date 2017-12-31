package xmu.crms.exception;
/**
 * @author zzj
 * */
public class SeminarNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public SeminarNotFoundException() {
        super();
    }

    public SeminarNotFoundException(String message) {
        super(message);
    }

    public SeminarNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SeminarNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public SeminarNotFoundException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SeminarNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public SeminarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeminarNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SeminarNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
