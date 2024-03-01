package com.blogapp12;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogappApplication.class, args);
	}


	@Bean //we can use bean annotation only in configuration file
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
