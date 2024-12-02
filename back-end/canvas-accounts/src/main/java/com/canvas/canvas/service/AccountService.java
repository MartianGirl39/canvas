package com.canvas.canvas.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.canvas.canvas.config.JwtAuthenticationToken;
import com.canvas.canvas.feign.LogoInterface;
import com.canvas.canvas.model.Account;
import com.canvas.canvas.repo.AccountRepo;


import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AccountService {
	
	@Autowired
	AccountRepo repo;
	@Autowired
	LogoInterface service;
	
	public Account getAccount(String username) {
		return repo.findAccountByUsername(username);
	}
	
	public Page<Account> getAccounts(int limit, int offset, String keyword) {
		Pageable pageable = PageRequest.of(offset, limit, Sort.by("user").ascending());
		return repo.findByUsernameContainingIgnoreCase(keyword, pageable);
	}
	
	@Transactional
	public Account updateAccount(String newUsername, MultipartFile file, String currentUsername) {
		Account currentAccount = repo.findAccountByUsername(currentUsername);
		String filename = currentAccount.getLogo();
		if (file != null && !file.isEmpty()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        // Assume the token is stored in the "Bearer <token>" format in the credentials
	        String token = ((JwtAuthenticationToken) authentication).getCredentialsAsString(); // Replace with your token retrieval logic
			
			filename = service.uploadNewLogo(file, token);
			currentAccount.setLogo(filename);
		}
		if (!newUsername.isBlank() && newUsername != null) {
			currentAccount.setUsername(newUsername);
		}
		return repo.save(currentAccount);
	}
}
