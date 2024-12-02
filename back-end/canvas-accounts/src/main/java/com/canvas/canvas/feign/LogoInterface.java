package com.canvas.canvas.feign;

import java.security.Principal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient(name="CANVAS-IMAGE")
public interface LogoInterface {
	@PostMapping("/api/file/image/logo")
	String uploadNewLogo(MultipartFile file,  @RequestHeader("Authorization") String authHeader);
}
