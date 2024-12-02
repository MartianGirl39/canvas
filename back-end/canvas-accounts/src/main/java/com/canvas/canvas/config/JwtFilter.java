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

public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtService service;
	
	@Autowired ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// grab token from header
		String authHeader = request.getHeader("Authorization");
		String token = null; // start null in case there is no token
		
		// check if authorization header is not null and is indeed a jwt token
		if (authHeader != null && authHeader.startsWith("Bearer")) {
			// grab token from authorization
			token = authHeader.substring(7);
		}
		System.out.println("VALIDATING TOKEN: " + token);
		if (token != null && service.validateToken(token)) {
			String username = service.extractUserName(token);	// extract the user from the token
			List<String> roles = service.extractRoles(token); // now extract the roles from the token
			JwtAuthenticationToken authToken = new JwtAuthenticationToken(username, roles, token);
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		filterChain.doFilter(request, response);
	}

}
