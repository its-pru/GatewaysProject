package exceptions;

public class UnableToUpdateKeyException extends Exception{
    public UnableToUpdateKeyException(){}
    public UnableToUpdateKeyException(String message){
        super(message);
    }
}
