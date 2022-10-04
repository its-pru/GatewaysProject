package Exceptions;

import java.lang.Exception;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(){}

    public AlreadyExistsException(String message){
        super(message);
    }
}
