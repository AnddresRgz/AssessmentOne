package utils;

public class LoadTransactionsException extends Exception{
    public LoadTransactionsException(String message, Throwable e){
        super(message, e);
    }
}
