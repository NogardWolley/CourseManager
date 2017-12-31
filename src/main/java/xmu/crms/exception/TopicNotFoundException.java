package xmu.crms.exception;
/**
 * @author zzj
 * */
public class TopicNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public TopicNotFoundException() {
        super();
    }

    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TopicNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public TopicNotFoundException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public TopicNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TopicNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
