package com.canvas.canvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	public Store findByStoreName(String name);
	public Store findByStoreOwner(String owner);
	public boolean existsByStoreOwner(String owner);
}
