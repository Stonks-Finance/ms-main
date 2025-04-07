package az.ufaz.stock_predictor.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import az.ufaz.stock_predictor.client.StockPredictorAIClient;
import az.ufaz.stock_predictor.exception.StockPredictionException;
import az.ufaz.stock_predictor.exception.UnacceptableInputException;
import az.ufaz.stock_predictor.mapper.StockPredictorMapper;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorBaseDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorSimpleStockDTO;
import az.ufaz.stock_predictor.model.dto.response.BaseResponse;
import az.ufaz.stock_predictor.model.dto.response.SimpleStockResponse;
import az.ufaz.stock_predictor.model.enums.StockPredictionSimpleStockInterval;
import az.ufaz.stock_predictor.service.provider.StockPredictorServiceDataProvider;

@ExtendWith(value = MockitoExtension.class)
public class StockPredictorServiceTests 
{
    private StockPredictorService service; 
    private StockPredictorAIClient stockPredictorAIClient; 
    private StockPredictorMapper stockPredictorMapper; 

    @BeforeEach
    public void setUp()
    {
        stockPredictorAIClient = Mockito.mock(StockPredictorAIClient.class);
        stockPredictorMapper = Mappers.getMapper(StockPredictorMapper.class); 
        service = new StockPredictorService(stockPredictorAIClient, stockPredictorMapper);
    }

    @ParameterizedTest
    @ArgumentsSource(value = StockPredictorServiceDataProvider.StockPredictionProviderForEverythingIsOkCase.class)
    public void givenStockPrediction_WhenEverythingIsOk_ThenReturnStockPredictions(
        String stockName, 
        StockPredictionSimpleStockInterval interval, 
        int duration, 
        String intervalString,
        StockPredictorSimpleStockDTO predictionDto, 
        int status,
        List<SimpleStockResponse> stockResponseList
    ){
        Mockito.when(stockPredictorAIClient.getStockPrediction(stockName, intervalString, duration))
            .thenReturn(
                StockPredictorBaseDTO.<StockPredictorSimpleStockDTO>builder()
                    .data(predictionDto)
                    .message("Predictions made successfully.")
                    .success(true)
                    .status(status)
                    .build()
            );

        BaseResponse<List<SimpleStockResponse>> serviceResponse = service.getStockPrediction(stockName, interval, duration);

        Assertions.assertEquals(status, serviceResponse.getStatus());
        Assertions.assertEquals("Predictions made successfully.", serviceResponse.getMessage());
        Assertions.assertEquals(stockResponseList, serviceResponse.getData());
    }

    @Test
    public void givenStockPrediction_WhenDurationIsNotGreaterThanZero_ThenThrowUnacceptableInputException()
    {
        String expectedExceptionMessage = "Duration must be greater than 0."; 
        UnacceptableInputException exception = null; 

        try
        {
            service.getStockPrediction("AAPL", StockPredictionSimpleStockInterval.ONE_HOUR, 0); 
        }
        catch(UnacceptableInputException e)
        {
            exception = e;
        }

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());        
    }

    @ParameterizedTest
    @ArgumentsSource(value = StockPredictorServiceDataProvider.StockPredictionProviderForClientResponseIsNotSuccessCase.class)
    public void givenStockPrediction_WhenClientResponseIsNotSuccess_ThenThrowStockPredictionException(
        String stockName, 
        StockPredictionSimpleStockInterval interval, 
        int duration, 
        String intervalString, 
        int clientStatus,
        String exceptionMessage
    ){
        // Arrange
        StockPredictionException exception = null; 

        Mockito.when(stockPredictorAIClient.getStockPrediction(stockName, intervalString, duration))
            .thenReturn(
                StockPredictorBaseDTO.<StockPredictorSimpleStockDTO>builder()
                    .message(exceptionMessage)
                    .status(clientStatus)
                    .build()
            ); 

        // Act
        try
        {
            service.getStockPrediction(stockName, interval, duration); 
        }
        catch(StockPredictionException e)
        {
            exception = e; 
        }

        // Assert
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }
}
