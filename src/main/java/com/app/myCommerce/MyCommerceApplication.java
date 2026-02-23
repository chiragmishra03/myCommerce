package com.app.myCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyCommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyCommerceApplication.class, args);
	}

}
