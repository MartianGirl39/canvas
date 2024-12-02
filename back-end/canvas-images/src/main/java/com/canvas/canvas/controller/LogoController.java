package com.canvas.canvas.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.config.JwtAuthenticationToken;
import com.canvas.canvas.service.FileService;


@RestController
@RequestMapping("/api/file/image/logo")
public class LogoController {
	
	@Autowired
	FileService service;
	
	@GetMapping("")
	public byte[] getAccountLogo(@RequestParam String file) {
		return service.getFile("logo/" + file);
	}
	
	@PostMapping("")
	public String uploadNewLogo(@RequestPart MultipartFile file, Principal principal) {
		if (principal instanceof JwtAuthenticationToken) {
			JwtAuthenticationToken authToken = (JwtAuthenticationToken) principal;
			String username = authToken.getPrincipal().toString();
			if (username.isBlank()) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "are you logged in as a user");
			}
			return service.upload("/logo/" + username + "-logo", file).getOutcome();
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "are you logged in as a user");
	}
	
	@GetMapping("/store")
	public byte[] getStoreLogo(@RequestParam String file) {
		return service.getFile("logo/" + file);
	}
	
	@PostMapping("/store")
	public String uploadNewStoreLogo(@RequestPart MultipartFile file, Principal principal) {
		if (principal instanceof JwtAuthenticationToken) {
			JwtAuthenticationToken authToken = (JwtAuthenticationToken) principal;
			String username = authToken.getPrincipal().toString();
			if (username.isBlank()) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "are you logged in as a user");
			}
			// eventually just check for store?
			return service.upload("/logo/" + username + "store-logo", file).getOutcome();
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "are you logged in as a user");
	}
}
