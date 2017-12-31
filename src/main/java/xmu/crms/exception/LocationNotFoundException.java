package xmu.crms.exception;

public class LocationNotFoundException extends Exception{
    public LocationNotFoundException(String message) {
        super(message);
    }
    public LocationNotFoundException() {
        super("找不到此位置");
    }
}
