/*package com.example.workflow.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.workflow.model.User;
import com.example.workflow.services.UserService;

import lombok.extern.log4j.Log4j;
@Log4j
@Configuration
public class MyUserDetailsService implements UserDetailsService {
	UserService userService;
	InMemoryUserDetailsManager inMemoryUserDetailsManager;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername:"+username);
		return userService.getUserDetailsByUsername(username);
	}
	@Bean
	@Primary
	@Qualifier("UserDetailsService")
	public UserDetailsService MyUserDetailsService() {
		inMemoryUserDetailsManager=new InMemoryUserDetailsManager();
		List<User>users=userService.findAll();
	    for (User user : users) {
	    	log.info("loaded user: "+user);
	    	inMemoryUserDetailsManager.createUser(userService.getUserDetails(user));
	    }
		return inMemoryUserDetailsManager;
	}
}
*/