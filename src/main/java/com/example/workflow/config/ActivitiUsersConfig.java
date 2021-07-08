package com.example.workflow.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.workflow.controller.ProcessController;
import com.example.workflow.services.UserService;
import org.activiti.api.runtime.shared.identity.UserGroupManager;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
@Log4j
@Configuration
public class ActivitiUsersConfig {
   @Bean
   @Primary
    public UserDetailsService myUserDetailsService() {

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        
        String[][] usersGroupsAndRoles = {
                {"admin", "password", "ROLE_ACTIVITI_ADMIN"},
                {"user", "password", "ROLE_ACTIVITI_USER"},
                {"muhamed", "password", "ROLE_ACTIVITI_USER"},
                {"amin", "password", "ROLE_ACTIVITI_USER"},
                {"mustafa", "password", "ROLE_ACTIVITI_USER"},
        };

        for (String[] user : usersGroupsAndRoles) {
            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
            log.info("> Registering new user: " + user[0] + " with the following Authorities[" + authoritiesStrings + "]");
            inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
                    authoritiesStrings.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList())));
        }


        return inMemoryUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
    	return  NoOpPasswordEncoder.getInstance();
    }	
}


