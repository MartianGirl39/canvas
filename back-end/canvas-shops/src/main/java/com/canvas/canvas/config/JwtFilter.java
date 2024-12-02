package com.canvas.canvas.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.canvas.canvas.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtService service;
	@Autowired
    ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		
		System.out.println("VALIDATING TOKEN");
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			System.out.println(token);
		}

		
		if (token != null && service.validateToken(token)) {
            String username = service.extractUserName(token);
            List<String> roles = service.extractRoles(token);  
            JwtAuthenticationToken authToken = new JwtAuthenticationToken(username, roles, token);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.print("is valid");
        }
		filterChain.doFilter(request, response);
	}
}

