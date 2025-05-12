package app.exception;

public class AccountLockException extends RuntimeException{
    public AccountLockException() {
        super();
    }

    public AccountLockException(String message) {
        super(message);
    }
}
