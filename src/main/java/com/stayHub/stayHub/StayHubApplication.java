package com.stayHub.stayHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StayHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(StayHubApplication.class, args);
	}

}
