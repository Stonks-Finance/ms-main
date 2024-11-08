package az.ufaz.stock_predictor.exception;

public class PastStockValuesException extends RuntimeException
{
    public PastStockValuesException(String message)
    {
        super(message);
    }    
}
