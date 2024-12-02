package com.canvas.canvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.canvas.canvas.config.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableDiscoveryClient
public class CanvasImagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanvasImagesApplication.class, args);
	}

}
