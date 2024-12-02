package com.canvas.canvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CanvasServerRegistryApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(CanvasServerRegistryApplication2.class, args);
	}

}
