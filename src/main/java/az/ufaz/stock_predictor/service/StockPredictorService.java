package az.ufaz.stock_predictor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import az.ufaz.stock_predictor.client.StockPredictorAIClient;
import az.ufaz.stock_predictor.exception.PastStockValuesException;
import az.ufaz.stock_predictor.exception.StockPredictionException;
import az.ufaz.stock_predictor.exception.UnacceptableInputException;
import az.ufaz.stock_predictor.mapper.StockPredictorMapper;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorBaseDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorSimpleStockDTO;
import az.ufaz.stock_predictor.model.dto.response.BaseResponse;
import az.ufaz.stock_predictor.model.dto.response.SimpleStockResponse;
import az.ufaz.stock_predictor.model.enums.StockPredictionInterval;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockPredictorService 
{
    private final StockPredictorAIClient stockPredictorAIClient;
    private final StockPredictorMapper stockPredictorMapper; 

    private Optional<String> getStockPredictionIntervalString(StockPredictionInterval interval)
    {
        switch(interval)
        {
            case ONE_HOUR: 
                return Optional.of("1h"); 
            case ONE_MINUTE: 
                return Optional.of("1m");
            default: 
                return Optional.empty(); 
        }
    }

    public BaseResponse<List<SimpleStockResponse>> getStockPrediction(
        String stockName, StockPredictionInterval interval, int duration
    ){
        StockPredictorBaseDTO<StockPredictorSimpleStockDTO> prediction; 

        if (duration <= 0) 
        {
            String message = "Duration must be greater than 0."; 
            log.info("Error in stock prediction : {}", message);
            throw new UnacceptableInputException(message); 
        }

        String intervalString = getStockPredictionIntervalString(interval).orElseThrow(() -> {
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
        String stockName, StockPredictionInterval interval, int duration 
    ){
        StockPredictorBaseDTO<StockPredictorSimpleStockDTO> prediction;

        if (duration <= 0) 
        {
            String message = "Duration must be greater than 0."; 
            log.info("Error in stock prediction : {}", message);
            throw new UnacceptableInputException(message); 
        }

        String intervalString = getStockPredictionIntervalString(interval).orElseThrow(() -> {
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

        log.info("Past values made successfully for stock prediction: {}, interval: {}, duration: {}.", stockName, interval, duration);

        return BaseResponse.<List<SimpleStockResponse>>builder()
                .data(stockResponses)
                .message("Past values made successfully.")
                .status(HttpStatus.OK.value())
                .success(true)
                .build();
    }
}
