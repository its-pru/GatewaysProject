package exceptions;

import java.lang.Exception;

public class UnableToUpdateException extends Exception {

    public UnableToUpdateException() {
    }

    public UnableToUpdateException(String message) {
        super(message);
    }
}
