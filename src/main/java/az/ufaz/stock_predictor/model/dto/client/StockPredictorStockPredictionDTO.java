package az.ufaz.stock_predictor.model.dto.client;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockPredictorStockPredictionDTO 
{
    private List<Double> prices; 
    private List<String> timestamps; 
}
