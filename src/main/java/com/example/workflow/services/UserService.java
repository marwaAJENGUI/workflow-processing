package com.example.workflow.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.example.workflow.config.MyUserDetails;
import com.example.workflow.model.User;
import com.example.workflow.repository.UserRepository;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class UserService /* implements UserDetailsService*/{
	@Autowired
	UserRepository userRepository;

	public Optional<User> getUserByUsername(String username) { 
        return userRepository.findByUsername(username);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User addUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User updateUser(User user, Long id) {
		return userRepository.saveAndFlush(user);
	}

	public boolean removeUser(Long id) {
		Optional<User> userOpt=userRepository.findById(id);
    	if (userOpt.isPresent()) {
			userRepository.deleteById(id);
    	}
    	return userOpt.isPresent();
	}

    public UserDetails getUserDetailsByUsername(String username) {
    	Optional<User> userOpt=userRepository.findByUsername(username);
    	MyUserDetails userDetails=null;
    	if (userOpt.isPresent()) {
	    	userDetails.setUserName(userOpt.get().getUsername());
	    	userDetails.setPassword(userOpt.get().getPassword());
	    	userDetails.setAuthorities (Arrays.stream(userOpt.get().getRole().split(","))
	                    .map(SimpleGrantedAuthority::new)
	                    .collect(Collectors.toList()));
	    	}
    	return userDetails;
    }
    public UserDetails getUserDetails(User user) {
    	MyUserDetails userDetails=null;
    	if (user!=null) {
	    	userDetails.setUserName(user.getUsername());
	    	userDetails.setPassword(user.getPassword());
	    	userDetails.setAuthorities (Arrays.stream(user.getRole().split(","))
	                    .map(SimpleGrantedAuthority::new)
	                    .collect(Collectors.toList()));
	    	}
    	return userDetails;
    }
/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUserDetailsByUsername(username);
	}
*/

}
