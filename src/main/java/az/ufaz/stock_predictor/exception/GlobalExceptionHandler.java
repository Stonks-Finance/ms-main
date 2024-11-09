package az.ufaz.stock_predictor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import az.ufaz.stock_predictor.model.dto.response.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = StockPredictionException.class)
    public BaseResponse<Void> handleStockPredictionException(StockPredictionException exception)
    {
        return BaseResponse.<Void>builder()
                .message(exception.getMessage())
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .build(); 
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PastStockValuesException.class)
    public BaseResponse<Void> handlePastStockValuesException(PastStockValuesException exception)
    {
        return BaseResponse.<Void>builder()
                .message(exception.getMessage())
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = StockOverviewException.class)
    public BaseResponse<Void> handleStockOverviewException(StockOverviewException exception)
    {
        return BaseResponse.<Void>builder()
                .message(exception.getMessage())
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = UnacceptableInputException.class)
    public BaseResponse<Void> handleUnacceptableInputException(UnacceptableInputException exception)
    {
        return BaseResponse.<Void>builder()
                .message(exception.getMessage())
                .success(false)
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .build();
    }
}
