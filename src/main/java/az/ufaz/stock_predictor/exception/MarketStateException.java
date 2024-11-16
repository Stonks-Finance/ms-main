package az.ufaz.stock_predictor.exception;

public class MarketStateException extends RuntimeException
{
    public MarketStateException(String message)
    {
        super(message);
    }
}
