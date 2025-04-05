package az.ufaz.stock_predictor.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import az.ufaz.stock_predictor.client.StockPredictorAIClient;
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
    @ArgumentsSource(value = StockPredictorServiceDataProvider.StockPredictionProvider.class)
    @DisplayName(value = "Testing stock prediction when everything is ok")
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
}
