package az.ufaz.stock_predictor.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import az.ufaz.stock_predictor.client.StockPredictorAIClient;
import az.ufaz.stock_predictor.exception.MarketStateException;
import az.ufaz.stock_predictor.exception.PastStockValuesException;
import az.ufaz.stock_predictor.exception.StockModelCreationException;
import az.ufaz.stock_predictor.exception.StockOverviewException;
import az.ufaz.stock_predictor.exception.StockPredictionException;
import az.ufaz.stock_predictor.exception.UnacceptableInputException;
import az.ufaz.stock_predictor.mapper.StockPredictorMapper;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorBaseDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorDetailedStockDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorSimpleStockDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorStockOverviewDTO;
import az.ufaz.stock_predictor.model.dto.response.BaseResponse;
import az.ufaz.stock_predictor.model.dto.response.DetailedStockResponse;
import az.ufaz.stock_predictor.model.dto.response.SimpleStockResponse;
import az.ufaz.stock_predictor.model.dto.response.StockMarketStateResponse;
import az.ufaz.stock_predictor.model.dto.response.StockOverviewResponse;
import az.ufaz.stock_predictor.model.enums.StockPredictionDetailedStockInterval;
import az.ufaz.stock_predictor.model.enums.StockPredictionSimpleStockInterval;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockPredictorService 
{
    private final StockPredictorAIClient stockPredictorAIClient;
    private final StockPredictorMapper stockPredictorMapper; 

    private Optional<String> getStockPredictionShortIntervalString(StockPredictionSimpleStockInterval interval)
    {
        switch(interval)
        {
            case ONE_HOUR: 
                return Optional.of("1h"); 
            case ONE_MINUTE: 
                return Optional.of("1m");
            case FIVE_MINUTE: 
                return Optional.of("5m");
            default: 
                return Optional.empty(); 
        }
    }

    private Optional<String> getStockPredictionLongIntervalString(StockPredictionDetailedStockInterval interval)
    {
        switch(interval)
        {
            case ONE_DAY: 
                return Optional.of("1d"); 
            case ONE_MONTH: 
                return Optional.of("1mo");
            case ONE_HOUR: 
                return Optional.of("1h");
            default: 
                return Optional.empty(); 
        }
    }

    public BaseResponse<List<SimpleStockResponse>> getStockPrediction(
        String stockName, StockPredictionSimpleStockInterval interval, int duration
    ){
        StockPredictorBaseDTO<StockPredictorSimpleStockDTO> prediction; 

        if (duration <= 0) 
        {
            String message = "Duration must be greater than 0."; 
            log.info("Error in stock prediction : {}", message);
            throw new UnacceptableInputException(message); 
        }

        String intervalString = getStockPredictionShortIntervalString(interval).orElseThrow(() -> {
            String message = "The interval entered is invalid."; 
            log.info("Error in stock prediction : {}", message); 
            return new UnacceptableInputException(message);
        }); 

        prediction = stockPredictorAIClient.getStockPrediction(stockName, intervalString, duration);

        if (!prediction.isSuccess())
        {
            log.info("Error in stock prediction : {}", prediction.getMessage());
            throw new StockPredictionException(prediction.getMessage());
        }

        List<SimpleStockResponse> stockResponses = stockPredictorMapper.predictionDTOToListOfStockResponse(
            prediction.getData()
        ); 

        log.info("Predictions made successfully for stock: {}, interval: {}, duration: {}.", stockName, interval, duration);

        return BaseResponse.<List<SimpleStockResponse>>builder()
                .data(stockResponses)
                .message("Predictions made successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build(); 
    }

    public BaseResponse<List<SimpleStockResponse>> getPastValuesOfSimpleStock(
        String stockName, StockPredictionSimpleStockInterval interval, int duration 
    ){
        StockPredictorBaseDTO<StockPredictorSimpleStockDTO> prediction;

        if (duration <= 0) 
        {
            String message = "Duration must be greater than 0."; 
            log.info("Error in stock prediction : {}", message);
            throw new UnacceptableInputException(message); 
        }

        String intervalString = getStockPredictionShortIntervalString(interval).orElseThrow(() -> {
            String message = "The interval entered is invalid."; 
            log.info("Error in stock prediction : {}", message); 
            return new UnacceptableInputException(message);
        });

        prediction = stockPredictorAIClient.getPastValuesOfSimpleStock(stockName, intervalString, duration);

        if (!prediction.isSuccess())
        {
            log.info("Error in stock prediction : {}", prediction.getMessage());
            throw new PastStockValuesException(prediction.getMessage());
        }

        List<SimpleStockResponse> stockResponses = stockPredictorMapper.predictionDTOToListOfStockResponse(
            prediction.getData()
        );

        log.info("Past values made successfully for simple stock : {}, interval: {}, duration: {}.", stockName, interval, duration);

        return BaseResponse.<List<SimpleStockResponse>>builder()
                .data(stockResponses)
                .message("Past values made successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    public BaseResponse<List<DetailedStockResponse>> getPastValuesOfDetailedStock(
        String stockName, StockPredictionDetailedStockInterval interval, int duration
    ){
        StockPredictorBaseDTO<List<StockPredictorDetailedStockDTO>> prediction;

        if (duration <= 0) 
        {
            String message = "Duration must be greater than 0."; 
            log.info("Error in stock prediction : {}", message);
            throw new UnacceptableInputException(message); 
        }

        String intervalString = getStockPredictionLongIntervalString(interval).orElseThrow(() -> {
            String message = "The interval entered is invalid."; 
            log.info("Error in stock prediction : {}", message); 
            return new UnacceptableInputException(message);
        });

        prediction = stockPredictorAIClient.getPastValuesOfDetailedStock(stockName, intervalString, duration);

        if (!prediction.isSuccess())
        {
            log.info("Error in stock prediction : {}", prediction.getMessage());
            throw new PastStockValuesException(prediction.getMessage());
        }

        List<DetailedStockResponse> stockResponses = prediction.getData().stream().map(
            stockPredictorMapper::clientDTOToResponse
        ).collect(Collectors.toList());

        log.info("Past values made successfully for detailed stock : {}, interval: {}, duration: {}.", stockName, interval, duration);

        return BaseResponse.<List<DetailedStockResponse>>builder()
                .data(stockResponses)
                .message("Past values made successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    public BaseResponse<List<StockOverviewResponse>> getStockOverview()
    {
        StockPredictorBaseDTO<List<StockPredictorStockOverviewDTO>> stockOverviewDTO = stockPredictorAIClient.getStockOverview();

        if(!stockOverviewDTO.isSuccess())
        {
            log.info("Error fetching stock overview: {}", stockOverviewDTO.getMessage());
            throw new StockOverviewException(stockOverviewDTO.getMessage());
        }
        List<StockOverviewResponse> responseList = stockOverviewDTO.getData().stream()
            .map(stockPredictorMapper::clientDTOToResponse)
            .collect(Collectors.toList());

        log.info("Stock overview retrieved successfully.");

        return BaseResponse.<List<StockOverviewResponse>>builder()
                .data(responseList)
                .message("Stock overview fetched succesfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    public BaseResponse<StockMarketStateResponse> getMarketState()
    {
        StockPredictorBaseDTO<Boolean> marketStateDTO = stockPredictorAIClient.getMarketState(); 

        if(!marketStateDTO.isSuccess())
        {
            log.info("Error fetching market state: {}", marketStateDTO.getMessage());
            throw new MarketStateException(marketStateDTO.getMessage());
        }

        StockMarketStateResponse response = stockPredictorMapper.clientDTOToResponse(marketStateDTO.getData());
        log.info("Market state retrieved successfully.");

        return BaseResponse.<StockMarketStateResponse>builder()
                .data(response)
                .message("Market state fetched succesfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    public BaseResponse<Void> createStockModel(String stockName)
    {
        StockPredictorBaseDTO<Void> createModelDTO = stockPredictorAIClient.createStockModel(stockName);

        if(!createModelDTO.isSuccess())
        {
            log.info("Error creating stock model: {}", createModelDTO.getMessage());
            throw new StockModelCreationException(createModelDTO.getMessage());
        }

        log.info("Stock model created successfully for stock: {}", stockName);

        return BaseResponse.<Void>builder()
                .message("Stock model created successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();
    }
}
