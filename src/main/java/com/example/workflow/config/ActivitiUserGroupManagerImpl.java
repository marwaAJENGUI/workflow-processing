package com.example.workflow.config;
/*
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.activiti.core.common.spring.identity.ExtendedInMemoryUserDetailsManager;
import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.workflow.model.Actions;
import com.example.workflow.model.Info;
import com.example.workflow.model.JavaClass;
import com.example.workflow.model.UserGroup;
import com.example.workflow.services.UserService;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
@Primary
public class ActivitiUserGroupManagerImpl implements UserGroupManager {

	UserService userService;

	@Override
	public List<String> getUserGroups(String username) {
		
		return (List)this.userService.getUserDetailsByUsername(username).getAuthorities().stream().filter((a) -> {
	        return a.getAuthority().startsWith("GROUP_");
	    }).map((a) -> {
	        return a.getAuthority().substring(6);
	    }).collect(Collectors.toList());
	}

	@Override
	public List<String> getUserRoles(String username) {
		return (List)this.userService.getUserDetailsByUsername(username).getAuthorities().stream().filter((a) -> {
	        return a.getAuthority().startsWith("ROLE_");
	    }).map((a) -> {
	        return a.getAuthority().substring(5);
	    }).collect(Collectors.toList());
	}

	@Override
	public List<String> getGroups() {
		List<String> list=EnumSet.allOf(UserGroup.class).stream().map(e ->e.toString()).collect(Collectors.toList());
		log.info("getGroups: "+ list);
		return list;
	}

	@Override
	public List<String> getUsers() {
		return userService.findAll()
				.stream()
				.map(user->user.getUsername())
				.collect(Collectors.toList());
	}

}
*/