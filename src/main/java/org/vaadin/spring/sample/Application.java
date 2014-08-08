package org.vaadin.spring.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.servlet.SpringAwareVaadinServlet;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	
	@Bean
	public SpringAwareVaadinServlet springAwareVaadinServlet() {
		return new CustomVaadinServlet();
	}

}
