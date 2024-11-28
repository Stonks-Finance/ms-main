package az.ufaz.stock_predictor;

import az.ufaz.stock_predictor.controller.StockPredictorController;
import az.ufaz.stock_predictor.exception.UnacceptableInputException;
import az.ufaz.stock_predictor.model.dto.response.*;
import az.ufaz.stock_predictor.model.enums.*;
import az.ufaz.stock_predictor.service.StockPredictorService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(StockPredictorController.class)
public class StockPredictorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockPredictorService stockPredictorService;

    /**
     * Test case: Successful prediction retrieval.
     * Endpoint: GET /api/v1/stock_predictor/predict
     */
    @Test
    void testGetStockPrediction_Success() throws Exception {
        // Arrange
        String stockName = "AAPL";
        StockPredictionSimpleStockInterval interval = StockPredictionSimpleStockInterval.ONE_HOUR;
        int duration = 5;

        List<SimpleStockResponse> stockResponses = Arrays.asList(
                SimpleStockResponse.builder()
                        .price(150.0)
                        .timestamp(LocalDateTime.of(2023, 10, 25, 10, 0))
                        .build(),
                SimpleStockResponse.builder()
                        .price(152.0)
                        .timestamp(LocalDateTime.of(2023, 10, 25, 11, 0))
                        .build()
        );

        BaseResponse<List<SimpleStockResponse>> baseResponse = BaseResponse.<List<SimpleStockResponse>>builder()
                .data(stockResponses)
                .message("Predictions made successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();

        when(stockPredictorService.getStockPrediction(eq(stockName), eq(interval), eq(duration)))
                .thenReturn(baseResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/stock_predictor/predict")
                        .param("stock_name", stockName)
                        .param("interval", interval.name())
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Predictions made successfully."))
                .andExpect(jsonPath("$.data[0].price").value(150.0))
                .andExpect(jsonPath("$.data[1].price").value(152.0));

        // Verify
        verify(stockPredictorService, times(1)).getStockPrediction(eq(stockName), eq(interval), eq(duration));
    }

    /**
     * Test case: Missing required parameters.
     * Endpoint: GET /api/v1/stock_predictor/predict
     */
    @Test
    void testGetStockPrediction_MissingParameters() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/v1/stock_predictor/predict"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test case: Invalid duration parameter.
     * Endpoint: GET /api/v1/stock_predictor/predict
     */
    @Test
    void testGetStockPrediction_InvalidDuration() throws Exception {
        // Arrange
        String stockName = "AAPL";
        StockPredictionSimpleStockInterval interval = StockPredictionSimpleStockInterval.ONE_HOUR;
        int duration = -1; // Invalid duration

        // Simulate the service throwing UnacceptableInputException
        when(stockPredictorService.getStockPrediction(eq(stockName), eq(interval), eq(duration)))
                .thenThrow(new UnacceptableInputException("Duration must be greater than 0."));

        // Act & Assert
        mockMvc.perform(get("/api/v1/stock_predictor/predict")
                        .param("stock_name", stockName)
                        .param("interval", interval.name())
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Duration must be greater than 0."));
    }

    /**
     * Test case: Successful retrieval of past simple stock values.
     * Endpoint: GET /api/v1/stock_predictor/past-values/simple
     */
    @Test
    void testGetPastValuesOfSimpleStock_Success() throws Exception {
        // Arrange
        String stockName = "AAPL";
        StockPredictionSimpleStockInterval interval = StockPredictionSimpleStockInterval.ONE_HOUR;
        int duration = 10;

        List<SimpleStockResponse> stockResponses = Arrays.asList(
                SimpleStockResponse.builder()
                        .price(148.0)
                        .timestamp(LocalDateTime.of(2023, 10, 25, 9, 0))
                        .build(),
                SimpleStockResponse.builder()
                        .price(149.0)
                        .timestamp(LocalDateTime.of(2023, 10, 25, 9, 1))
                        .build()
        );

        BaseResponse<List<SimpleStockResponse>> baseResponse = BaseResponse.<List<SimpleStockResponse>>builder()
                .data(stockResponses)
                .message("Past values made successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();

        when(stockPredictorService.getPastValuesOfSimpleStock(eq(stockName), eq(interval), eq(duration)))
                .thenReturn(baseResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/stock_predictor/past-values/simple")
                        .param("stock_name", stockName)
                        .param("interval", interval.name())
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Past values made successfully."))
                .andExpect(jsonPath("$.data[0].price").value(148.0))
                .andExpect(jsonPath("$.data[1].price").value(149.0));

        // Verify
        verify(stockPredictorService, times(1)).getPastValuesOfSimpleStock(eq(stockName), eq(interval), eq(duration));
    }

    /**
     * Test case: Successful retrieval of past detailed stock values.
     * Endpoint: GET /api/v1/stock_predictor/past-values/detailed
     */
    @Test
    void testGetPastValuesOfDetailedStock_Success() throws Exception {
        // Arrange
        String stockName = "AAPL";
        StockPredictionDetailedStockInterval interval = StockPredictionDetailedStockInterval.ONE_DAY;
        int duration = 7;

        List<DetailedStockResponse> stockResponses = Arrays.asList(
                DetailedStockResponse.builder()
                        .timestamp(LocalDateTime.of(2023, 10, 18,0,0,0))
                        .open(145.0)
                        .high(150.0)
                        .low(144.0)
                        .close(149.0)
                        .build(),
                DetailedStockResponse.builder()
                        .timestamp(LocalDateTime.of(2023, 10, 19,0,0,0))
                        .open(149.5)
                        .high(151.0)
                        .low(148.0)
                        .close(150.0)
                        .build()
        );

        BaseResponse<List<DetailedStockResponse>> baseResponse = BaseResponse.<List<DetailedStockResponse>>builder()
                .data(stockResponses)
                .message("Past values made successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();

        when(stockPredictorService.getPastValuesOfDetailedStock(eq(stockName), eq(interval), eq(duration)))
                .thenReturn(baseResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/stock_predictor/past-values/detailed")
                        .param("stock_name", stockName)
                        .param("interval", interval.name())
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Past values made successfully."))
                .andExpect(jsonPath("$.data[0].open").value(145.0))
                .andExpect(jsonPath("$.data[1].open").value(149.5));

        // Verify
        verify(stockPredictorService, times(1)).getPastValuesOfDetailedStock(eq(stockName), eq(interval), eq(duration));
    }

    /**
     * Test case: Invalid interval parameter for detailed stock values.
     * Endpoint: GET /api/v1/stock_predictor/past-values/detailed
     */
    @Test
    void testGetPastValuesOfDetailedStock_InvalidInterval() throws Exception {
        // Arrange
        String stockName = "AAPL";
        String invalidInterval = "INVALID_INTERVAL";
        int duration = 7;

        // Act & Assert
        mockMvc.perform(get("/api/v1/stock_predictor/past-values/detailed")
                        .param("stock_name", stockName)
                        .param("interval", invalidInterval)
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test case: Service throws an exception when predicting stock.
     * Endpoint: POST /api/v1/stock_predictor/predict
     */
    @Test
    void testGetStockPrediction_ServiceException() throws Exception {
        // Arrange
        String stockName = "AAPL";
        StockPredictionSimpleStockInterval interval = StockPredictionSimpleStockInterval.ONE_HOUR;
        int duration = 5;

        when(stockPredictorService.getStockPrediction(eq(stockName), eq(interval), eq(duration)))
                .thenThrow(new UnacceptableInputException("Invalid input parameters."));

        // Act & Assert
        mockMvc.perform(post("/api/v1/stock_predictor/predict")
                        .param("stock_name", stockName)
                        .param("interval", interval.name())
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid input parameters."));

        // Verify
        verify(stockPredictorService, times(1)).getStockPrediction(eq(stockName), eq(interval), eq(duration));
    }

    /**
     * Test case: Missing stock_name parameter.
     * Endpoint: POST /api/v1/stock_predictor/predict
     */
    @Test
    void testGetStockPrediction_MissingStockName() throws Exception {
        // Arrange
        StockPredictionSimpleStockInterval interval = StockPredictionSimpleStockInterval.ONE_HOUR;
        int duration = 5;

        // Act & Assert
        mockMvc.perform(post("/api/v1/stock_predictor/predict")
                        .param("interval", interval.name())
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test case: Invalid interval parameter type.
     * Endpoint: GET /api/v1/stock_predictor/predict
     */
    @Test
    void testGetStockPrediction_InvalidInterval() throws Exception {
        // Arrange
        String stockName = "AAPL";
        String invalidInterval = "INVALID_INTERVAL";
        int duration = 5;

        // Act & Assert
        mockMvc.perform(get("/api/v1/stock_predictor/predict")
                        .param("stock_name", stockName)
                        .param("interval", invalidInterval)
                        .param("duration", String.valueOf(duration)))
                .andExpect(status().isBadRequest());
    }



}