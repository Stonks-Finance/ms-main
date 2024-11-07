package az.ufaz.stock_predictor.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stock-predictor-client", url = "${application.url.stock-predictor-ai}")
public interface StockPredictorAIClient 
{
    
}
