package com.example.flatB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlatBApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlatBApplication.class, args);
	}

}
