package com.canvas.canvas.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canvas.canvas.model.PriceSettings;
import com.canvas.canvas.model.dto.PriceSettingsDto;
import com.canvas.canvas.service.PriceSettingsService;

@RestController
@RequestMapping("/api/store/pricing")
public class PriceSettingsController {
	
	@Autowired
	private PriceSettingsService settings;
	
	@GetMapping("")
	public List<PriceSettings> getAllSettings(Principal principal) {
		return settings.getPriceSettings(principal.getName());
	}
	
	@PostMapping("")
	public void createSetting(@RequestBody PriceSettingsDto dto, Principal principal) {
		settings.createNewSetting(dto, principal.getName());
	}
}
