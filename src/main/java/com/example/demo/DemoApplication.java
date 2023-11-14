package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import io.springfox.documentation.

@SpringBootApplication
//@EnableSwagger2
@ComponentScan(basePackages = {"com.example","com.example.service"})
@EnableJpaRepositories("com.example.repository")
@EntityScan("com.example.model")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
