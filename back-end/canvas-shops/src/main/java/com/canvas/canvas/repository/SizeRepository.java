package com.canvas.canvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.model.Size;


@Repository
public interface SizeRepository extends JpaRepository<Size, Long>{
	public Size findByDescription(String decsription);
}
