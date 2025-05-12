package app.exception;

public class CardInformationIncorrectException extends RuntimeException{
    public CardInformationIncorrectException() {
        super("Card Information Incorrect");
    }

    public CardInformationIncorrectException(String message) {
        super(message);
    }
}
