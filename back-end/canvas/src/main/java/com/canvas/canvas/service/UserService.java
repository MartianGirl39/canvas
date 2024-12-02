package com.canvas.canvas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.models.Account;
import com.canvas.canvas.models.User;
import com.canvas.canvas.models.dto.RegisterDto;
import com.canvas.canvas.repository.AccountRepository;
import com.canvas.canvas.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtService jwtService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@Transactional
	public User register(RegisterDto user) {
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		System.out.println(user);
		newUser.setPassword(encoder.encode(user.getPassword()));
		
		if (userRepo.existsByEmail(user.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exists with this email");
		}
		
		User createdUser = userRepo.save(newUser);
		
		if (createdUser != null) {
			
			Account account = new Account();
			account.setUsername(user.getUsername());
			account.setUser(newUser);
			account.setLogoUrl("default-logo.png");
			
			if (accountRepo.existsByUsername(account.getUsername())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exists with this username");
			}
			
			Account newAccount = accountRepo.save(account);
			System.out.print(newAccount.getUsername());
			
			if (newAccount != null) {
				return createdUser;
			}
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to create new user");
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "failed to create new user");
	}

	public String verify(User user) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		User logging = userRepo.findByEmail(user.getEmail());
		if (auth.isAuthenticated() && logging != null) {
			Account account = accountRepo.findByUserId(logging.getId());
			if (account != null) return jwtService.generateToken(account.getUsername());
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid user");
	}
}
