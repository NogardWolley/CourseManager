package xmu.crms.exception;
/**
 * @author zzj
 * */
public class GroupNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public GroupNotFoundException() {
        super();
    }

    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GroupNotFoundException(String errorCode) {
        this.errorCode = errorCode;
    }

    public GroupNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GroupNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public GroupNotFoundException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public GroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
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
