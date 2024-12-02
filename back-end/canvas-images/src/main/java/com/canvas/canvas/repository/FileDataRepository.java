package com.canvas.canvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.canvas.canvas.model.FileData;

public interface FileDataRepository extends JpaRepository<FileData, String> {
	FileData findByUri(String uri);
}
