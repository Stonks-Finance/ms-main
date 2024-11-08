package az.ufaz.stock_predictor.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import az.ufaz.stock_predictor.client.StockPredictorAIClient;
import az.ufaz.stock_predictor.exception.StockPredictionException;
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

    public BaseResponse<List<SimpleStockResponse>> getStockPrediction(
        String stockName, StockPredictionInterval interval, int duration
    ){
        StockPredictorBaseDTO<StockPredictorSimpleStockDTO> prediction; 
        String intervalString; 

        if (duration <= 0) 
        {
            String message = "Duration must be greater than 0."; 
            log.info("Error in stock prediction : {}", message);
            throw new StockPredictionException(message); 
        }

        switch(interval)
        {
            case ONE_HOUR: 
                intervalString = "1h"; 
                break; 
            case ONE_MINUTE: 
                intervalString = "1m";
                break; 
            default: 
                String message = "The interval entered is invalid."; 
                log.info("Error in stock prediction : {}", message); 
                throw new StockPredictionException(message);
        }

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
}
