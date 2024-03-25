package com.happiness.githerbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class GitherbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitherbsApplication.class, args);
	}

}
