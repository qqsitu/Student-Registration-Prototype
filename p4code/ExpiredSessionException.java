package p4code;

public class ExpiredSessionException extends Exception{
    public ExpiredSessionException(String message) {
        super(message);
    }
}
