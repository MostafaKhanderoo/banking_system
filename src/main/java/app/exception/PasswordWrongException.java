package app.exception;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException(String message) {
        super(message);
    }

    public PasswordWrongException() {
    }
}
