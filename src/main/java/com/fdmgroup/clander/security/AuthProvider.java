package com.fdmgroup.clander.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fdmgroup.clander.services.UserService;

@Component
public class AuthProvider implements AuthenticationProvider{

	private final UserService userService;
	
	public AuthProvider(UserService userService) {
		this.userService = userService;
	}
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		userService.setAuthenticationObject(authentication);
		
		UserDetails userDetails = userService.loadUserByUsername(name);
		
		String password = authentication.getCredentials().toString();
		
		if(!password.equals(userDetails.getPassword()))
			throw new BadCredentialsException("Incorrect password");
		
		return new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
