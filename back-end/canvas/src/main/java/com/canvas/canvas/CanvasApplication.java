package com.canvas.canvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.canvas.canvas")
@EnableDiscoveryClient
public class CanvasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanvasApplication.class, args);
	}

}
