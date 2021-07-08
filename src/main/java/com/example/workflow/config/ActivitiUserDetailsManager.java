package com.example.workflow.config;
/*
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.workflow.model.User;
import com.example.workflow.services.UserService;

import lombok.extern.log4j.Log4j;
@Log4j
public class ActivitiUserDetailsManager extends InMemoryUserDetailsManager  {
	UserService userService;
	ActivitiUserGroupManagerImpl groupManager;
	
	public UserDetailsService ActivitiUserDetailsManager(UserService userService,ActivitiUserGroupManagerImpl groupManager) {	
		this.userService=userService;
		this.groupManager=groupManager;
		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager();
		List<User>users=userService.findAll();
	    for (User user : users) {
	    	log.info("loaded user: "+user);
	    	inMemoryUserDetailsManager.createUser(user.toUserDetails());
	    }
		return inMemoryUserDetailsManager;
		}
		
	private List<String> users = new ArrayList<>();
    private List<String> groups = new ArrayList<>();

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername:"+username);
		return userService.getUserDetailsByUsername(username);
	}
    
    @Override
    public void createUser(UserDetails user) {
        super.createUser(user);
        users.add(user.getUsername());
        groups = user.getAuthorities().stream()
                .filter(x -> (x.getAuthority().contains("GROUP")))
                .map(x -> (x.getAuthority()))
                .collect(Collectors.toList());
        log.info("createUser->groupManager.getUserGroups:"+groupManager.getUserGroups(user.getUsername()));
    }

    public List<String> getUsers() {
        return groupManager.getUsers();
    }

    public List<String> getGroups() {
        return groupManager.getGroups();
    }
}
*/