package az.ufaz.stock_predictor.model.dto.client;

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
    private String timestamp; 
    private double open;
    private double high;
    private double low;
    private double close;
}
