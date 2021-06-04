package com.example.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProcessRunningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessRunningApplication.class, args);
	}


	@Bean
	public UserDetailsService userDetailsService() {
	
	    InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
	
	    String[][] usersGroupsAndRoles = {
	            {"user", "password", "ROLE_ACTIVITI_USER"},
	            {"admin", "password", "ROLE_ACTIVITI_ADMIN"},
	    };
		return inMemoryUserDetailsManager;
	}
}
