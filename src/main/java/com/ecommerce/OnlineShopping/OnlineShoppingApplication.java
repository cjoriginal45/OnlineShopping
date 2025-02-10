package com.ecommerce.OnlineShopping;
import com.ecommerce.OnlineShopping.models.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApplication.class, args);
                
                Configuration config = Configuration.getInstancia();
                
                config.setParametro("nombreTienda", "OnlineShopping");
	}

}
