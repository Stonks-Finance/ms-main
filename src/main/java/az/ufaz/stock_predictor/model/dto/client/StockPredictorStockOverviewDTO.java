package az.ufaz.stock_predictor.model.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockPredictorStockOverviewDTO 
{
    @JsonProperty(value = "stock_name")
    private String stockName; 
    private String change; 
}
