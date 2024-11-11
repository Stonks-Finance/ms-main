package az.ufaz.stock_predictor.model.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailedStockResponse 
{
    private LocalDateTime timestamp; 
    private double open;
    private double high;
    private double low;
    private double close;
}
