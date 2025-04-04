package az.ufaz.stock_predictor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import az.ufaz.stock_predictor.client.StockPredictorAIClient;
import az.ufaz.stock_predictor.mapper.StockPredictorMapper;

@ExtendWith(value = MockitoExtension.class)
public class StockPredictorServiceTests 
{
    private StockPredictorService stockPredictorService; 
    private StockPredictorAIClient stockPredictorAIClient; 
    private StockPredictorMapper stockPredictorMapper; 

    @BeforeEach
    public void setUp()
    {
        stockPredictorAIClient = Mockito.mock(StockPredictorAIClient.class);
        stockPredictorMapper = Mappers.getMapper(StockPredictorMapper.class); 
        stockPredictorService = new StockPredictorService(stockPredictorAIClient, stockPredictorMapper);
    }

    
}
