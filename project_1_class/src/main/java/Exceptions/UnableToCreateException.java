package Exceptions;

import java.lang.Exception;

public class UnableToCreateException extends Exception {

    public UnableToCreateException() {
    }

    public UnableToCreateException(String message) {
        super(message);
    }
}
