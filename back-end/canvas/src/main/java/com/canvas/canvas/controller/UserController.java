package com.canvas.canvas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.models.User;
import com.canvas.canvas.models.dto.RegisterDto;
import com.canvas.canvas.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public User register(@RequestBody RegisterDto user) {
		System.out.println("REGISTERING USER");
		return service.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		System.out.println("LOGGING IN USER");
		return service.verify(user);
	}
	
//	@PreAuthorize("isAuthenticated()")
//	@PutMapping("/update/user/{id}")
//	public User changePassword(@PathVariable long id, User user, Principal principal) {
//		if (!principal.getName().equals(user.getEmail())) {
//			// throw status code
//			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you must be logged in to change password");
//		}
//		user.setId(id);
//		Account account = User 
//		return service.register(user);
//	}
}
