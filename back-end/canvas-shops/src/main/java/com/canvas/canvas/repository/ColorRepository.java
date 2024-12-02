package com.canvas.canvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.model.Color;


@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
	public Color findByColorName(String colorName);
}
