package xmu.crms.exception;

import java.io.Serializable;

/**
 * @author zzj
 * */
public class CourseNotFoundException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    public CourseNotFoundException() {
        super();
    }

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CourseNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CourseNotFoundException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public CourseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public CourseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CourseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
