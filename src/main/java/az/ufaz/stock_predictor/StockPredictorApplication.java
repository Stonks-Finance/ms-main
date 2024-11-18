package az.ufaz.stock_predictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockPredictorApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(StockPredictorApplication.class, args);
	}
}
