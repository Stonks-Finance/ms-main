package az.ufaz.stock_predictor.service.provider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.http.HttpStatus;

import az.ufaz.stock_predictor.model.dto.client.StockPredictorSimpleStockDTO;
import az.ufaz.stock_predictor.model.dto.response.SimpleStockResponse;
import az.ufaz.stock_predictor.model.enums.StockPredictionSimpleStockInterval;

public class StockPredictorServiceDataProvider 
{
    public static class StockPredictionProvider implements ArgumentsProvider
    {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context)
        {
            return Stream.of(
                Arguments.of(
                    "AAPL", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                193.3159942626953, 
                                192.48828125,
                                191.85888671875,
                                191.50152587890625,
                                191.41998291015625
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-04T20:30:00+00:00",
                                "2025-04-04T21:30:00+00:00",
                                "2025-04-04T22:30:00+00:00",
                                "2025-04-04T23:30:00+00:00",
                                "2025-04-05T00:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(193.3159942626953)
                            .timestamp(LocalDateTime.of(2025, 4, 4, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(192.48828125)
                            .timestamp(LocalDateTime.of(2025, 4, 4, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(191.85888671875)
                            .timestamp(LocalDateTime.of(2025, 4, 4, 22, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(191.50152587890625)
                            .timestamp(LocalDateTime.of(2025, 4, 4, 23, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(191.41998291015625)
                            .timestamp(LocalDateTime.of(2025, 4, 5, 0, 30, 0))
                            .build()
                    )
                )
            );
        }
    }
}
