package az.ufaz.stock_predictor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import az.ufaz.stock_predictor.client.StockPredictorAIClient;
import az.ufaz.stock_predictor.exception.*;
import az.ufaz.stock_predictor.mapper.StockPredictorMapper;
import az.ufaz.stock_predictor.model.dto.client.*;
import az.ufaz.stock_predictor.model.dto.response.*;
import az.ufaz.stock_predictor.model.enums.*;
import az.ufaz.stock_predictor.service.StockPredictorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class StockPredictorServiceTest {

    @InjectMocks
    private StockPredictorService stockPredictorService;

    @Mock
    private StockPredictorAIClient stockPredictorAIClient;

    @Mock
    private StockPredictorMapper stockPredictorMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test getStockPrediction method
    @Test
    void testGetStockPrediction_Success() {
        // Arrange
        String stockName = "AAPL";
        StockPredictionShortInterval interval = StockPredictionShortInterval.ONE_HOUR;
        int duration = 5;
        String intervalString = "1h";

        StockPredictorSimpleStockDTO simpleStockDTO = StockPredictorSimpleStockDTO.builder()
                .prices(Arrays.asList(150.0, 151.0, 152.0))
                .timestamps(Arrays.asList(
                        "2023-10-25T10:00:00Z",
                        "2023-10-25T11:00:00Z",
                        "2023-10-25T12:00:00Z"))
                .build();

        StockPredictorBaseDTO<StockPredictorSimpleStockDTO> predictionDTO = StockPredictorBaseDTO.<StockPredictorSimpleStockDTO>builder()
                .data(simpleStockDTO)
                .message("Success")
                .success(true)
                .status(HttpStatus.OK.value())
                .build();

        List<SimpleStockResponse> expectedResponses = Arrays.asList(
                SimpleStockResponse.builder()
                        .price(150.0)
                        .timestamp(stockPredictorMapper.stringToLocalDateTime("2023-10-25T10:00:00Z"))
                        .build(),
                SimpleStockResponse.builder()
                        .price(151.0)
                        .timestamp(stockPredictorMapper.stringToLocalDateTime("2023-10-25T11:00:00Z"))
                        .build(),
                SimpleStockResponse.builder()
                        .price(152.0)
                        .timestamp(stockPredictorMapper.stringToLocalDateTime("2023-10-25T12:00:00Z"))
                        .build()
        );

        when(stockPredictorAIClient.getStockPrediction(stockName, intervalString, duration))
                .thenReturn(predictionDTO);

        when(stockPredictorMapper.predictionDTOToListOfStockResponse(simpleStockDTO))
                .thenReturn(expectedResponses);

        // Act
        BaseResponse<List<SimpleStockResponse>> response = stockPredictorService.getStockPrediction(stockName, interval, duration);

        // Assert
        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Predictions made successfully.", response.getMessage());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedResponses, response.getData());

        verify(stockPredictorAIClient, times(1)).getStockPrediction(stockName, intervalString, duration);
        verify(stockPredictorMapper, times(1)).predictionDTOToListOfStockResponse(simpleStockDTO);
    }

    @Test
    void testGetStockPrediction_InvalidDuration() {
        // Arrange
        String stockName = "AAPL";
        StockPredictionShortInterval interval = StockPredictionShortInterval.ONE_HOUR;
        int duration = 0;

        // Act & Assert
        UnacceptableInputException exception = assertThrows(UnacceptableInputException.class, () ->
                stockPredictorService.getStockPrediction(stockName, interval, duration));

        assertEquals("Duration must be greater than 0.", exception.getMessage());

        verifyNoInteractions(stockPredictorAIClient);
        verifyNoInteractions(stockPredictorMapper);
    }

    @Test
    void testGetStockPrediction_ClientError() {
        // Arrange
        String stockName = "AAPL";
        StockPredictionShortInterval interval = StockPredictionShortInterval.ONE_HOUR;
        int duration = 5;
        String intervalString = "1h";

        StockPredictorBaseDTO<StockPredictorSimpleStockDTO> predictionDTO = StockPredictorBaseDTO.<StockPredictorSimpleStockDTO>builder()
                .data(null)
                .message("Error fetching prediction")
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        when(stockPredictorAIClient.getStockPrediction(stockName, intervalString, duration))
                .thenReturn(predictionDTO);

        // Act & Assert
        StockPredictionException exception = assertThrows(StockPredictionException.class, () ->
                stockPredictorService.getStockPrediction(stockName, interval, duration));

        assertEquals("Error fetching prediction", exception.getMessage());

        verify(stockPredictorAIClient, times(1)).getStockPrediction(stockName, intervalString, duration);
        verifyNoInteractions(stockPredictorMapper);
    }

}

