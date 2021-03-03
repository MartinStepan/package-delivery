package bsc.packagedelivery.exceptions;

public class ExtensionException extends Exception {

    /**
     * Custom exception for bad or missing file extension
     *
     * @param errorMessage
     */
    public ExtensionException(String errorMessage) {
        super(errorMessage);
    }
}
