package cl.duoc.ms_product_bs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsProductBsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductBsApplication.class, args);
	}

}
