package az.ufaz.stock_predictor;

import static org.junit.jupiter.api.Assertions.*;

import az.ufaz.stock_predictor.mapper.StockPredictorMapper;
import az.ufaz.stock_predictor.mapper.StockPredictorMapperImpl;
import az.ufaz.stock_predictor.model.dto.client.*;
import az.ufaz.stock_predictor.model.dto.response.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class StockPredictorMapperTest {

    private StockPredictorMapper stockPredictorMapper = new StockPredictorMapperImpl();

    @Test
    void testClientDTOToResponse() {
        // Arrange
        StockPredictorDetailedStockDTO detailedDTO = StockPredictorDetailedStockDTO.builder()
                .date("2023-10-25")
                .open(100.0)
                .high(110.0)
                .low(90.0)
                .close(105.0)
                .build();

        // Act
        DetailedStockResponse response = stockPredictorMapper.clientDTOToResponse(detailedDTO);

        // Assert
        assertNotNull(response);
        assertEquals(LocalDate.of(2023, 10, 25), response.getDate());
        assertEquals(100.0, response.getOpen());
        assertEquals(110.0, response.getHigh());
        assertEquals(90.0, response.getLow());
        assertEquals(105.0, response.getClose());
    }

    @Test
    void testPredictionDTOToListOfStockResponse() {
        // Arrange
        StockPredictorSimpleStockDTO simpleStockDTO = StockPredictorSimpleStockDTO.builder()
                .prices(Arrays.asList(150.0, 151.0, 152.0))
                .timestamps(Arrays.asList(
                        "2023-10-25T10:00:00Z",
                        "2023-10-25T11:00:00Z",
                        "2023-10-25T12:00:00Z"))
                .build();

        // Act
        List<SimpleStockResponse> responses = stockPredictorMapper.predictionDTOToListOfStockResponse(simpleStockDTO);

        // Assert
        assertNotNull(responses);
        assertEquals(3, responses.size());

        SimpleStockResponse response1 = responses.get(0);
        assertEquals(150.0, response1.getPrice());
        assertEquals(LocalDateTime.parse("2023-10-25T10:00:00"), response1.getTimestamp());

        SimpleStockResponse response2 = responses.get(1);
        assertEquals(151.0, response2.getPrice());
        assertEquals(LocalDateTime.parse("2023-10-25T11:00:00"), response2.getTimestamp());

        SimpleStockResponse response3 = responses.get(2);
        assertEquals(152.0, response3.getPrice());
        assertEquals(LocalDateTime.parse("2023-10-25T12:00:00"), response3.getTimestamp());
    }

    @Test
    void testStringToLocalDateTime() {
        // Arrange
        String timeString = "2023-10-25T10:00:00Z";

        // Act
        LocalDateTime result = stockPredictorMapper.stringToLocalDateTime(timeString);

        // Assert
        assertEquals(LocalDateTime.parse("2023-10-25T10:00:00"), result);
    }
}