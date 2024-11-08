package az.ufaz.stock_predictor.exception;

public class UnacceptableInputException extends RuntimeException
{
    public UnacceptableInputException(String message)
    {
        super(message);
    }    
}
