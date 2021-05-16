package com.kundan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReStFullCurdOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReStFullCurdOperationApplication.class, args);
	}

}
