package com.canvas.canvas.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.model.Store;
import com.canvas.canvas.model.dto.StoreDto;
import com.canvas.canvas.service.StoreService;

	    		
@RestController
@RequestMapping("/api/store")
public class StoreController {
	
	@Autowired
	StoreService service;
	
	@GetMapping("")
	public Store getUserStore(Principal principal) {
		if (principal == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Are you logged in as a current user?");
		}
		return service.getStore(principal.getName());
	}
	
	@PostMapping("")
	public Store createStore(Principal principal, @RequestPart StoreDto newStore, @RequestPart Optional<MultipartFile> logo) {
		if (principal == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "");
		}
		newStore.setUsername(principal.getName());
		return service.createStore(newStore, logo);
	}
	
	@PutMapping("")
	public Store updateStore(Principal principal, @RequestPart StoreDto newStore, @RequestPart Optional<MultipartFile> logo) {
		if (principal == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "");
		}
		newStore.setUsername(principal.getName());
		return service.updateStore(newStore, logo);
	}
}
