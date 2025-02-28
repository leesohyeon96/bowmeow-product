package com.bowmeow.bowmeow_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.bowmeow.bowmeow_product.entity")
@SpringBootApplication
public class BowmeowProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowmeowProductApplication.class, args);
	}

}
