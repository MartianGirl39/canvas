package com.canvas.canvas.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.config.StorageProperties;
import com.canvas.canvas.model.dto.UriDto;

import io.jsonwebtoken.lang.Arrays;


@Service
public class FileService {
	
	@Autowired
	private StorageProperties props;
	
	private final Path ROOT_LOCATION;
	private final Map<String, List<String>> ALLOWED_EXTENSIONS = new HashMap<String, List<String>>();
	
	public FileService(StorageProperties props) {
		this.props = props;
		this.ROOT_LOCATION = Paths.get(props.getLocation());
		this.ALLOWED_EXTENSIONS.put("images", Arrays.asList(new String[]{".jpeg", ".png", ".svg"}));
	}
	
	public byte[] getFile(String fileName) {
		if (fileName.isBlank()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "somehow the filename is null");
		}
		System.out.println(ROOT_LOCATION);
		Path path = this.ROOT_LOCATION.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
		// if file doesn't exist, assume lack of extension?
		if (!fileName.contains(".")) {
			for (String extension : this.ALLOWED_EXTENSIONS.get("images")) {
				Path newPath = this.ROOT_LOCATION.resolve(Paths.get(fileName + extension)).normalize().toAbsolutePath();
				System.out.println(newPath.toString());
				if (Files.exists(newPath)) {
					path = newPath;
					break;
				}
			}
		}
		
		if (path == null || !Files.exists(path)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find file");
		}
		
		try {
			return Files.readAllBytes(path);
		} 
		catch(IOException exception) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not read file");
		}
	}
	
	public UriDto upload(String location, MultipartFile file) {
		UriDto newFile = new UriDto();
	
		try {
			if (file.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "a file must be given to upload");
			}
			String extension = this.getFileExtension(file);
			if (extension.isBlank()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "file is either missing an extension or is a disallowed extension");
			}
			Path destinationFile = this.ROOT_LOCATION.resolve(Paths.get(location) + "." + extension).normalize().toAbsolutePath();
			newFile.setFileType(extension);
			newFile.setUri(destinationFile.getFileName().toString());
			newFile.setFileSize(file.getSize());
			newFile.setOutcome("new file created");
			if (Files.exists(destinationFile)) {
				newFile.setOutcome("new file written over");
			}
			System.out.println("destination is " + destinationFile);
			if (!destinationFile.getParent().equals(this.ROOT_LOCATION.toAbsolutePath())) {
				// This is a security check, throw some exception
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "destination given is forbidden and unsafe");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "upload failed");
		}
		
		return newFile;
	}
	
	private String getFileExtension(MultipartFile file) {
	    String fileName = file.getOriginalFilename();
	    
	    if (fileName != null && fileName.contains(".")) {
	    	return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	    }
	    return "";
	}
}
