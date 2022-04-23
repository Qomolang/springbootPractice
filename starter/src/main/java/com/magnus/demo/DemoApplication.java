package com.magnus.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 84028
 */
@SpringBootApplication(scanBasePackages = {"com.magnus.demo","com.magnus.service","com.magnus.graphql"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
