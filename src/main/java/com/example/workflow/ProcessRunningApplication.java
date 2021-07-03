package com.example.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan({"com.example.workflow.*"})
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ProcessRunningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessRunningApplication.class, args);
	}
	
}
