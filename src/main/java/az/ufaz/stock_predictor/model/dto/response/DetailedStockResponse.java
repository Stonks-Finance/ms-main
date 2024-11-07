package az.ufaz.stock_predictor.model.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty(value = "time")
    private LocalDate date; 
    private double open;
    private double high;
    private double low;
    private double close;
}
