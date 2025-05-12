package app.exception;

public class NationalCodeExistsException extends RuntimeException {
    public NationalCodeExistsException() {
        super("nationalCode Exists!");
    }

    public NationalCodeExistsException(String message) {
        super(message);
    }
}
