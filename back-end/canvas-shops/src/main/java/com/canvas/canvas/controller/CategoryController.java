package com.canvas.canvas.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canvas.canvas.model.ItemType;
import com.canvas.canvas.service.CategoryService;

@RestController
@RequestMapping("/api/store/category")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	@GetMapping("")
	public Map<String, List<ItemType>> getByStore(@PathVariable String cat) {
		return service.getCategoryByStore(cat);
	}
	
	@GetMapping("/{cat}/sub-category/")
	public List<ItemType> getItemTypeByCategory(@PathVariable String cat, Principal principal) {
		return service.getItemTypeByCategories(cat, principal.getName());
	}
	
	@PostMapping("")
	public void addNewCategory(@RequestBody String name, Principal principal) {
		service.createNewCategory(name, principal.getName());
	}
	
	@PostMapping("/{cat}/sub-category/")
	public void addNewItemType(@PathVariable String cat, @RequestBody String name, Principal principal) {
		service.createNewItemType(cat, name, principal.getName());
	}
}
