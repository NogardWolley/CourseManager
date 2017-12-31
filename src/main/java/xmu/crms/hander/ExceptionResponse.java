package xmu.crms.hander;

import org.springframework.web.bind.annotation.ControllerAdvice;
/**
 * 
 * @author Administrator
 *
 */
@ControllerAdvice
public class ExceptionResponse {
    String message;
    String error;

    public ExceptionResponse()
    { }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public ExceptionResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
