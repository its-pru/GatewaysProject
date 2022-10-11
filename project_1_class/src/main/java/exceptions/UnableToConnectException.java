package exceptions;

import java.lang.Exception;

public class UnableToConnectException extends Exception {
    public UnableToConnectException() {
    }

    public UnableToConnectException(String message) {
        super(message);
    }
}
