package bsc.packagedelivery.exceptions;

public class IncorrectInputException extends Exception {

    /**
     * Own exception if incorrect input is readed
     * @param errorMessage
     */
    public IncorrectInputException(String errorMessage) {
        super(errorMessage);
    }
}
