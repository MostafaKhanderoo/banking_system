package app.exception;

public class UserNameExistsException extends RuntimeException{
    public UserNameExistsException() {
        super("Username Exists!");
    }

    public UserNameExistsException(String message) {
        super(message);
    }
}
