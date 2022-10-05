package exceptions;

public class UnableToGetKeyException extends Exception{

    public UnableToGetKeyException(){}
    public UnableToGetKeyException(String message){
        super(message);
    }
}
