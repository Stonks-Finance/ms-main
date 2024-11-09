package az.ufaz.stock_predictor.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import az.ufaz.stock_predictor.model.dto.client.StockPredictorDetailedStockDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorSimpleStockDTO;
import az.ufaz.stock_predictor.model.dto.client.StockPredictorStockOverviewDTO;
import az.ufaz.stock_predictor.model.dto.response.DetailedStockResponse;
import az.ufaz.stock_predictor.model.dto.response.SimpleStockResponse;
import az.ufaz.stock_predictor.model.dto.response.StockOverviewResponse;

@Mapper(componentModel = "spring")
public interface StockPredictorMapper 
{
    @Mapping(source = "date", target = "date", qualifiedByName = "stringToLocalDate")
    public DetailedStockResponse clientDTOToResponse(StockPredictorDetailedStockDTO clientDTO); 

    public StockOverviewResponse clientDTOToResponse(StockPredictorStockOverviewDTO clientDTO);

    public default List<SimpleStockResponse> predictionDTOToListOfStockResponse(
        StockPredictorSimpleStockDTO stockPredictionDTO
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

    @Named(value = "stringToLocalDate")
    public default LocalDate stringToLocalDate(String timeString)
    {
        return LocalDate.parse(timeString); 
    }
}
