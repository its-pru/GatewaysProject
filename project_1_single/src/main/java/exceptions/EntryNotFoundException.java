package exceptions;

import java.lang.Exception;

public class EntryNotFoundException extends Exception {
    public EntryNotFoundException(){}

    public EntryNotFoundException(String message){
        super(message);
    }
}
