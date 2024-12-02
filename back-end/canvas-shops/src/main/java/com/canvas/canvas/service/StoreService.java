package com.canvas.canvas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.feign.LogoInterface;
import com.canvas.canvas.model.Store;
import com.canvas.canvas.model.dto.StoreDto;
import com.canvas.canvas.repository.StoreRepository;

@Service
public class StoreService {
	
	@Autowired
	StoreRepository repo;
	@Autowired
	LogoInterface logoService;
	
	public Store getStore(String username) {
		return repo.findByStoreOwner(username);
	}
	
	public Store createStore(StoreDto newStore, Optional<MultipartFile> logo) {
		// check if store exists
		if (repo.existsByStoreOwner(newStore.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this user already has a store");
		}
		Store store = new Store();
		store.setStoreOwner(newStore.getUsername());
		store.setDescription(newStore.getDescription());
		store.setStoreName(newStore.getName());
		if (logo.isPresent()) {
			String logoUri = logoService.uploadNewLogo(logo.get(), null);
			store.setStoreLogo(logoUri);
		}
		store.setCustomUrl(null);
		repo.save(store);
		return store;
	}
	
	public Store updateStore(StoreDto newStore, Optional<MultipartFile> file) {
		Store store = repo.findByStoreOwner(newStore.getUsername());
		if (file.isPresent()) {
			store.setStoreLogo(logoService.uploadNewLogo(file.get(), null));
		}
		store.setDescription(newStore.getDescription());
		repo.save(store);
		return store;
	}
}
