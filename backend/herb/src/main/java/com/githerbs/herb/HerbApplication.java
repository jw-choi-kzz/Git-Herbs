package com.githerbs.herb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HerbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerbApplication.class, args);
	}

}
