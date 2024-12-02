package com.canvas.canvas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.model.CategoryType;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long>{
	public List<CategoryType> findByStoreStoreOwner(String storeOwner);
	public CategoryType findByDescription(String description);
}
