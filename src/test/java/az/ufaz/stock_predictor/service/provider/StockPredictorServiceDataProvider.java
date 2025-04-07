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
                ), 
                Arguments.of(
                    "AAPL", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                181.9215545654297,
                                181.32907104492188,
                                181.1494598388672
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-07T19:30:00+00:00",
                                "2025-04-07T20:30:00+00:00",
                                "2025-04-07T21:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(181.9215545654297)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 19, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(181.32907104492188)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(181.1494598388672)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build()
                    )
                ), 
                Arguments.of(
                    "BABA", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                105.5920639038086,
                                105.86654663085938,
                                106.10931396484375,
                                106.29403686523438,
                                106.42002868652344
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-07T19:30:00+00:00",
                                "2025-04-07T20:30:00+00:00",
                                "2025-04-07T21:30:00+00:00",
                                "2025-04-07T22:30:00+00:00",
                                "2025-04-07T23:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(105.5920639038086)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 19, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(105.86654663085938)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(106.10931396484375)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(),
                        SimpleStockResponse.builder()
                            .price(106.29403686523438)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(106.42002868652344)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 23, 30, 0))
                            .build()
                    )
                ),
                Arguments.of(
                    "BABA", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                105.5920639038086,
                                105.86654663085938,
                                106.10931396484375
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-07T19:30:00+00:00",
                                "2025-04-07T20:30:00+00:00",
                                "2025-04-07T21:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(105.5920639038086)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 19, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(105.86654663085938)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(106.10931396484375)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build()
                    )
                ),
                Arguments.of(
                    "DIS", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                83.15826416015625,
                                83.20012664794922,
                                83.27393341064453,
                                83.36383819580078,
                                83.45977783203125
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-07T20:30:00+00:00",
                                "2025-04-07T21:30:00+00:00",
                                "2025-04-07T22:30:00+00:00",
                                "2025-04-07T23:30:00+00:00",
                                "2025-04-08T00:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(83.15826416015625)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(83.20012664794922)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(83.27393341064453)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build(),
                        SimpleStockResponse.builder()
                            .price(83.36383819580078)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 23, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(83.45977783203125)
                            .timestamp(LocalDateTime.of(2025, 4, 8, 0, 30, 0))
                            .build()
                    )
                ),
                Arguments.of(
                    "DIS", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                83.15826416015625,
                                83.20012664794922,
                                83.27393341064453
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-07T20:30:00+00:00",
                                "2025-04-07T21:30:00+00:00",
                                "2025-04-07T22:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(83.15826416015625)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(83.20012664794922)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(83.27393341064453)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build()
                    )
                ), 
                Arguments.of(
                    "AMZN", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                174.00782775878906,
                                174.19525146484375,
                                174.3585205078125,
                                174.50758361816406,
                                174.64901733398438
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-07T20:30:00+00:00",
                                "2025-04-07T21:30:00+00:00",
                                "2025-04-07T22:30:00+00:00",
                                "2025-04-07T23:30:00+00:00",
                                "2025-04-08T00:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(174.00782775878906)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(174.19525146484375)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(174.3585205078125)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build(),
                        SimpleStockResponse.builder()
                            .price(174.50758361816406)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 23, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(174.64901733398438)
                            .timestamp(LocalDateTime.of(2025, 4, 8, 0, 30, 0))
                            .build()
                    )
                ),
                Arguments.of(
                    "AMZN", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                174.00782775878906,
                                174.19525146484375,
                                174.3585205078125
                            )
                        )
                        .timestamps(
                            List.of(
                                "2025-04-07T20:30:00+00:00",
                                "2025-04-07T21:30:00+00:00",
                                "2025-04-07T22:30:00+00:00"
                            )
                        )
                        .build(),
                    HttpStatus.OK.value(),
                    List.of(
                        SimpleStockResponse.builder()
                            .price(174.00782775878906)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(174.19525146484375)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(174.3585205078125)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build()
                    )
                )
            );
        }
    }
}
