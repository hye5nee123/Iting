package com.iting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
// @MapperScan(basePackages = "com.example.demo.**.mapper")
public class ItingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItingApplication.class, args);
	}

}
