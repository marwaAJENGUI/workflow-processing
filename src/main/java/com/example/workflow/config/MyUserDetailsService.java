package com.example.workflow.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.workflow.model.User;
import com.example.workflow.services.UserService;
@Configuration
@Qualifier("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	UserService userService;
	InMemoryUserDetailsManager inMemoryUserDetailsManager;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userService.getUserDetailsByUsername(username);
	}

	public UserDetailsService MyUserDetailsService() {
		inMemoryUserDetailsManager=new InMemoryUserDetailsManager();
		List<User>users=userService.findAll();
	    for (User user : users) {
	    	inMemoryUserDetailsManager.createUser(new MyUserDetails(user));
	    }
		return inMemoryUserDetailsManager;
	}
}
