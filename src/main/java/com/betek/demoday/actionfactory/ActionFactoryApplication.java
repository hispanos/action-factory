package com.betek.demoday.actionfactory;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Action Factory", version = "1.0", description = "API para empresa Action Factory"))
public class ActionFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActionFactoryApplication.class, args);
	}
}
