package app.exception;

public class ProblemAccountException extends RuntimeException{
    public ProblemAccountException() {
        super();
    }

    public ProblemAccountException(String message) {
        super(message);
    }
}
