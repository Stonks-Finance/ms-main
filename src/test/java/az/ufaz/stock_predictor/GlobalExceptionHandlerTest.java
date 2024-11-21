package az.ufaz.stock_predictor;
import az.ufaz.stock_predictor.exception.GlobalExceptionHandler;
import az.ufaz.stock_predictor.exception.PastStockValuesException;
import az.ufaz.stock_predictor.exception.StockPredictionException;
import az.ufaz.stock_predictor.exception.UnacceptableInputException;
import az.ufaz.stock_predictor.model.dto.response.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    /**
     * Test case: handleStockPredictionException should return the correct BaseResponse.
     */
    @Test
    void testHandleStockPredictionException() {
        // Arrange
        String errorMessage = "Prediction error occurred";
        StockPredictionException exception = new StockPredictionException(errorMessage);

        // Act
        BaseResponse<Void> response = exceptionHandler.handleStockPredictionException(exception);

        // Assert
        assertNotNull(response, "Response should not be null");
        assertFalse(response.isSuccess(), "Success flag should be false");
        assertEquals(errorMessage, response.getMessage(), "Error message should match");
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus(), "Status code should be 400");
        assertNull(response.getData(), "Data should be null");
    }

    /**
     * Test case: handlePastStockValuesException should return the correct BaseResponse.
     */
    @Test
    void testHandlePastStockValuesException() {
        // Arrange
        String errorMessage = "Error fetching past stock values";
        PastStockValuesException exception = new PastStockValuesException(errorMessage);

        // Act
        BaseResponse<Void> response = exceptionHandler.handlePastStockValuesException(exception);

        // Assert
        assertNotNull(response, "Response should not be null");
        assertFalse(response.isSuccess(), "Success flag should be false");
        assertEquals(errorMessage, response.getMessage(), "Error message should match");
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus(), "Status code should be 400");
        assertNull(response.getData(), "Data should be null");
    }

    /**
     * Test case: handleUnacceptableInputException should return the correct BaseResponse.
     */
    @Test
    void testHandleUnacceptableInputException() {
        // Arrange
        String errorMessage = "Invalid input provided";
        UnacceptableInputException exception = new UnacceptableInputException(errorMessage);

        // Act
        BaseResponse<Void> response = exceptionHandler.handleUnacceptableInputException(exception);

        // Assert
        assertNotNull(response, "Response should not be null");
        assertFalse(response.isSuccess(), "Success flag should be false");
        assertEquals(errorMessage, response.getMessage(), "Error message should match");
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatus(), "Status code should be 406");
        assertNull(response.getData(), "Data should be null");
    }
}