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
    public static class StockPredictionProviderForEverythingIsOkCase implements ArgumentsProvider
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
                ),
                Arguments.of(
                    "RACE", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                400.99725341796875,
                                400.2518310546875,
                                400.0163269042969,
                                400.04583740234375,
                                400.20166015625
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
                            .price(400.99725341796875)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(400.2518310546875)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(400.0163269042969)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build(),
                        SimpleStockResponse.builder()
                            .price(400.04583740234375)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 23, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(400.20166015625)
                            .timestamp(LocalDateTime.of(2025, 4, 8, 0, 30, 0))
                            .build()
                    )
                ),
                Arguments.of(
                    "RACE", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                400.99725341796875,
                                400.2518310546875,
                                400.0163269042969
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
                            .price(400.99725341796875)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(400.2518310546875)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(400.0163269042969)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build()
                    )
                ), 
                Arguments.of(
                    "TSLA", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                230.84347534179688,
                                231.24440002441406,
                                231.71519470214844,
                                232.21348571777344,
                                232.7113800048828
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
                            .price(230.84347534179688)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(231.24440002441406)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(231.71519470214844)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build(),
                        SimpleStockResponse.builder()
                            .price(232.21348571777344)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 23, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(232.7113800048828)
                            .timestamp(LocalDateTime.of(2025, 4, 8, 0, 30, 0))
                            .build()
                    )
                ),
                Arguments.of(
                    "TSLA", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                230.84347534179688,
                                231.24440002441406,
                                231.71519470214844
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
                            .price(230.84347534179688)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(231.24440002441406)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(231.71519470214844)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build()
                    )
                ), 
                Arguments.of(
                    "NVDA", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                96.0804672241211,
                                96.33422088623047,
                                96.48168182373047,
                                96.57726287841797,
                                96.64466857910156
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
                            .price(96.0804672241211)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(96.33422088623047)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(96.48168182373047)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build(),
                        SimpleStockResponse.builder()
                            .price(96.57726287841797)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 23, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(96.64466857910156)
                            .timestamp(LocalDateTime.of(2025, 4, 8, 0, 30, 0))
                            .build()
                    )
                ),
                Arguments.of(
                    "NVDA", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                96.0804672241211,
                                96.33422088623047,
                                96.48168182373047
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
                            .price(96.0804672241211)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(96.33422088623047)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(96.48168182373047)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build()
                    )
                ), 
                Arguments.of(
                    "AMD", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    5, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                83.77082061767578,
                                83.86018371582031,
                                84.05424499511719,
                                84.30230712890625,
                                84.57881927490234
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
                            .price(83.77082061767578)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(83.86018371582031)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(84.05424499511719)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build(),
                        SimpleStockResponse.builder()
                            .price(84.30230712890625)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 23, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(84.57881927490234)
                            .timestamp(LocalDateTime.of(2025, 4, 8, 0, 30, 0))
                            .build()
                    )
                ), 
                Arguments.of(
                    "AMD", 
                    StockPredictionSimpleStockInterval.ONE_HOUR,
                    3, 
                    "1h",
                    StockPredictorSimpleStockDTO.builder()
                        .prices(
                            List.of(
                                83.77082061767578,
                                83.86018371582031,
                                84.05424499511719
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
                            .price(83.77082061767578)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 20, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(83.86018371582031)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 21, 30, 0))
                            .build(), 
                        SimpleStockResponse.builder()
                            .price(84.05424499511719)
                            .timestamp(LocalDateTime.of(2025, 4, 7, 22, 30, 0))
                            .build()
                    )
                )
            );
        }
    }

    public static class StockPredictionProviderForClientResponseIsNotSuccessCase implements ArgumentsProvider
    {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context)
        {
            return Stream.of(
                Arguments.of(
                    "aapl", 
                    StockPredictionSimpleStockInterval.ONE_HOUR, 
                    5, 
                    "1h", 
                    HttpStatus.NOT_FOUND.value(), 
                    "Model file not found for specified stock."
                ),
                Arguments.of(
                    "AAPL", 
                    StockPredictionSimpleStockInterval.ONE_HOUR, 
                    7, 
                    "1h", 
                    HttpStatus.BAD_REQUEST.value(), 
                    "Duration must be between 1 and 6 for interval '1h'."
                ),
                Arguments.of(
                    "a apl", 
                    StockPredictionSimpleStockInterval.ONE_HOUR, 
                    5, 
                    "1h", 
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                    "Internal server error: Found array with 0 sample(s) (shape=(0, 2)) while a minimum of 1 is required by MinMaxScaler."
                )
            );
        }
    }
}
