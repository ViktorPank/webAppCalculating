package com.pankin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class WebAppCalculatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppCalculatingApplication.class, args);

	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(12800));
		factory.setMaxRequestSize(DataSize.ofBytes(12800));
		return factory.createMultipartConfig();
	}

}
