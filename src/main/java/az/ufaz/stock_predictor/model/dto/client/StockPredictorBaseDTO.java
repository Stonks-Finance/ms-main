package az.ufaz.stock_predictor.model.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockPredictorBaseDTO <T>
{
    private T data; 
    private String message; 
    private boolean success; 
    private int status; 
}
