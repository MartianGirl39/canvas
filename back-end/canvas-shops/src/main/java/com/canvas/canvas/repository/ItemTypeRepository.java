package com.canvas.canvas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.model.ItemType;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
	public ItemType findByDescription(String description);
	public List<ItemType> findByStoreStoreName(String name);
	public List<ItemType> findByCategoryTypeDescriptionAndStoreStoreOwner(String description, String storeOwner);
}