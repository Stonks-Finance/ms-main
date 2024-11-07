package az.ufaz.stock_predictor.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import az.ufaz.stock_predictor.model.dto.client.StockPredictorStockPredictionDTO;
import az.ufaz.stock_predictor.model.dto.response.SimpleStockResponse;

@Mapper(componentModel = "spring")
public interface StockPredictorMapper 
{
    public default List<SimpleStockResponse> predictionDTOToListOfStockResponse(
        StockPredictorStockPredictionDTO stockPredictionDTO
    ){
        List<SimpleStockResponse> stockResponses = new ArrayList<>(); 
        int length = stockPredictionDTO.getPrices().size(); 

        for (int i = 0; i < length; i++)
        {
            stockResponses.add(
                SimpleStockResponse.builder()
                    .price(stockPredictionDTO.getPrices().get(i))
                    .timestamp(stringToLocalDateTime(stockPredictionDTO.getTimestamps().get(i)))
                    .build()   
            );
        }

        return stockResponses;
    }

    public default LocalDateTime stringToLocalDateTime(String timeString)
    {
        String dateTimeWithoutOffset = timeString.substring(0, 19);
        return LocalDateTime.parse(dateTimeWithoutOffset);
    }
}
