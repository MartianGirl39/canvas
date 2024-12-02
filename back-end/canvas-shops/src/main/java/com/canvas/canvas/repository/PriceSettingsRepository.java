package com.canvas.canvas.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.model.PriceSettings;
import com.canvas.canvas.model.Store;

@Repository
public interface PriceSettingsRepository extends JpaRepository<PriceSettings, Long>{
	    public List<PriceSettings> findByStoreStoreOwner(String owner);
}
