package app.exception;

public class CreditCardAlreadyExistsException extends RuntimeException {
    public CreditCardAlreadyExistsException() {
        super("CreditCard Already Exists For Customer!");
    }

    public CreditCardAlreadyExistsException(String message) {
        super(message);
    }
}
