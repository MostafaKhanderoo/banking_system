package app.exception;

public class CustomerHasAccountInThisBankException  extends RuntimeException{
    public CustomerHasAccountInThisBankException() {
        super("Customer Has Account In This Bank");
    }

    public CustomerHasAccountInThisBankException(String message) {
        super(message);
    }
}
