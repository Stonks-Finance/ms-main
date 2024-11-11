package az.ufaz.stock_predictor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import az.ufaz.stock_predictor.model.dto.response.BaseResponse;
import az.ufaz.stock_predictor.model.dto.response.DetailedStockResponse;
import az.ufaz.stock_predictor.model.dto.response.SimpleStockResponse;
import az.ufaz.stock_predictor.model.dto.response.StockOverviewResponse;
import az.ufaz.stock_predictor.model.enums.StockPredictionDetailedStockInterval;
import az.ufaz.stock_predictor.model.enums.StockPredictionSimpleStockInterval;
import az.ufaz.stock_predictor.service.StockPredictorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/stock_predictor")
public class StockPredictorController 
{
    private final StockPredictorService stockPredictorService; 

    private final String LOG_TEMPLATE = "{} request to /api/v1/stock_predictor{}";

    @PostMapping(value = "/predict")
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResponse<List<SimpleStockResponse>> getStockPrediction(
        @RequestParam(value = "stock_name", required = true) String stockName, 
        @RequestParam(value = "interval", required = false) StockPredictionSimpleStockInterval interval,
        @RequestParam(value = "duration", required =  false) int duration
    ){
        log.info(LOG_TEMPLATE, "POST", "/predict");
        return stockPredictorService.getStockPrediction(stockName, interval, duration); 
    }

    @GetMapping(value = "/past-values/simple")
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResponse<List<SimpleStockResponse>> getPastValuesOfSimpleStock(
        @RequestParam(value = "stock_name", required = true) String stockName,
        @RequestParam(value = "interval", required = false) StockPredictionSimpleStockInterval interval,
        @RequestParam(value = "duration", required =  false) int duration 
    ){
        log.info(LOG_TEMPLATE, "GET", "/past-values/simple");
        return stockPredictorService.getPastValuesOfSimpleStock(stockName, interval, duration); 
    }

    @GetMapping(value = "/past-values/detailed")
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResponse<List<DetailedStockResponse>> getPastValuesOfDetailedStock(
        @RequestParam(value = "stock_name", required = true) String stockName,
        @RequestParam(value = "interval", required = false) StockPredictionDetailedStockInterval interval,
        @RequestParam(value = "duration", required =  false) int duration 
    ){
        log.info(LOG_TEMPLATE, "GET", "/past-values/detailed");
        return stockPredictorService.getPastValuesOfDetailedStock(stockName, interval, duration);
    }

    @GetMapping("/stock-overview")
    public BaseResponse<List<StockOverviewResponse>> getStockOverview()
    {
        log.info(LOG_TEMPLATE, "GET", "/stock-overview");
        return stockPredictorService.getStockOverview();
    }

}
