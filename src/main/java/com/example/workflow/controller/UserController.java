package com.example.workflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.workflow.model.User;
import com.example.workflow.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	@Autowired
	UserService userService;
	@GetMapping("/")
	@ResponseBody
	public List<User> getUsers() {
		return userService.findAll();   
	}
	
	@GetMapping("/{id}")
	@ResponseBody
    public User getUser(@PathVariable("id") Long id){
		return  userService.getUserById(id).get();		
		}
	
	@PostMapping("/")
	@ResponseBody
	public User addUser(@RequestBody User user)
	{
		User u =userService.addUser(user);
		return u;
	}
	
	@PutMapping(value = "/{id}") 
	@ResponseBody
	public User updateUser(@PathVariable("id") Long id,@RequestBody User user) {
		if ( userService.getUserById(id).isPresent()){
			User u = userService.getUserById(id).get();
			u.setUsername(user.getUsername());
			u.setEmail(user.getEmail());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setPassword(user.getPassword());
			u.setRole(user.getRole());
			return userService.updateUser(u, id);
	}else throw new  IllegalArgumentException("Invalide user ID, user do not exist");
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
    public boolean removeadCategory(@PathVariable("id") Long id){
		return userService.removeUser(id);
    }
}