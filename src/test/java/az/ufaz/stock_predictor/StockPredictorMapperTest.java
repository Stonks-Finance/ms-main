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
}