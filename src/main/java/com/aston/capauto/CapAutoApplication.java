package com.aston.capauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CapAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapAutoApplication.class, args);
	}

}
