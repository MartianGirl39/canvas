package com.canvas.canvas.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.canvas.canvas.model.Account;
import com.canvas.canvas.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@GetMapping("/account")
	public Account getAccount(@RequestParam String username) {
		return service.getAccount(username);
	}
	
	@GetMapping("/accounts")
	public Page<Account> getAccounts(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue="0") int offset, @RequestParam(defaultValue="") String keyword) {
		return service.getAccounts(limit, offset, keyword);
	}
	
	@PutMapping("/account")
	public Account updateAccount(@RequestPart String newUsername, @RequestPart(required=false) MultipartFile logo, Principal principal) {
		return service.updateAccount(newUsername, logo, principal.getName());
	}
}
