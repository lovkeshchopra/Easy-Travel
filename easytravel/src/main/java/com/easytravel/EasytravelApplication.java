package com.easytravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EasytravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasytravelApplication.class, args);
	}

}
