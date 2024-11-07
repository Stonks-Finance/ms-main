package az.ufaz.stock_predictor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import az.ufaz.stock_predictor.model.dto.client.StockPredictorBaseDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorStockPredictionDTO;

@FeignClient(name = "stock-predictor-client", url = "${application.url.stock-predictor-ai}")
public interface StockPredictorAIClient 
{
    @GetMapping(value = "/predict")
    public StockPredictorBaseDTO<StockPredictorStockPredictionDTO> getStockPrediction(
        @RequestParam(value = "stock_name", required = true) String stockName, 
        @RequestParam(value = "interval", required = false) String interval, 
        @RequestParam(value = "duration", required =  false) int duration
    ); 
}
