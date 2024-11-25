package com.agiles.sorteos.capanegocios.capasnegocios;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;





@SpringBootApplication(scanBasePackages = {"com.agiles.sorteos"})
@EnableScheduling
public class CapasnegociosApplication {


	public static void main(String[] args) {
		SpringApplication.run(CapasnegociosApplication.class, args);

	}

	
}
