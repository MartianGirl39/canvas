package com.canvas.canvas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.model.CategoryType;
import com.canvas.canvas.model.ItemType;
import com.canvas.canvas.model.Store;
import com.canvas.canvas.repository.CategoryTypeRepository;
import com.canvas.canvas.repository.ItemTypeRepository;
import com.canvas.canvas.repository.StoreRepository;

@Service
public class CategoryService {
	
	@Autowired
	ItemTypeRepository types;
	@Autowired
	CategoryTypeRepository categories;
	@Autowired 
	StoreRepository storeRepo;
	
	public List<ItemType> getItemTypeByCategories(String category, String username) {
		return types.findByCategoryTypeDescriptionAndStoreStoreOwner(category, username);
	}
	
	public Map<String, List<ItemType>> getCategoryByStore(String username) {
		List<CategoryType> cats = this.categories.findByStoreStoreOwner(username);
		Map<String, List<ItemType>> t = new HashMap<>();
		for (CategoryType cat : cats) {
			t.put(cat.getDescription(),this.types.findByCategoryTypeDescriptionAndStoreStoreOwner(cat.getDescription(), username));
		}
		return t;
	}

	public void createNewCategory(String name, String username) {
		CategoryType cat = new CategoryType();
		cat.setDescription(name);
		Store store = storeRepo.findByStoreOwner(username);
		if (store == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "user does not have a store");
		cat.setStore(store);
		categories.save(cat);
	}

	public void createNewItemType(String cat, String name, String username) {
		ItemType type = new ItemType();
		type.setDescription(name);
		CategoryType category = categories.findByDescription(cat);
		if (category == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "category does not exist");
		if (category.getStore() != null && !category.getStore().getStoreOwner().equals(username)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot add to categories that are not assosicated with your store");
		Store store = storeRepo.findByStoreOwner(username);
		if (store == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "this suer does not have a store");
		type.setStore(store);
		types.save(type);
	}
}
