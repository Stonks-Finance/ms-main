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
public class StockPredictorDetailedStockDTO 
{
    @JsonProperty(value = "time")
    private String date; 
    private double open;
    private double high;
    private double low;
    private double close;
}
