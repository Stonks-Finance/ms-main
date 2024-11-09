package az.ufaz.stock_predictor.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import az.ufaz.stock_predictor.model.dto.client.StockPredictorBaseDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorDetailedStockDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorSimpleStockDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorStockOverviewDTO;

@FeignClient(name = "stock-predictor-client", url = "${application.url.stock-predictor-ai}")
public interface StockPredictorAIClient 
{
    @PostMapping(value = "/predict")
    public StockPredictorBaseDTO<StockPredictorSimpleStockDTO> getStockPrediction(
        @RequestParam(value = "stock_name", required = true) String stockName, 
        @RequestParam(value = "interval", required = false) String interval, 
        @RequestParam(value = "duration", required =  false) int duration
    ); 

    @GetMapping(value = "/past-values")
    public StockPredictorBaseDTO<StockPredictorSimpleStockDTO> getPastValuesOfSimpleStock(
        @RequestParam(value = "stock_name", required = true) String stockName,
        @RequestParam(value = "interval", required = false) String interval,
        @RequestParam(value = "duration", required =  false) int duration
    );

    @GetMapping(value = "/historical-data")
    public StockPredictorBaseDTO<List<StockPredictorDetailedStockDTO>> getPastValuesOfDetailedStock(
        @RequestParam(value = "stock_name", required = true) String stockName,
        @RequestParam(value = "interval", required = false) String interval,
        @RequestParam(value = "duration", required =  false) int duration
    );

    @GetMapping(value = "/stock-overview")
    public StockPredictorBaseDTO<List<StockPredictorStockOverviewDTO>> getStockOverview();
}
