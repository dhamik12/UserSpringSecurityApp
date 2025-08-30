package com.telusko.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.telusko.model.User;

public class UserExtraInformation implements UserDetails {
	
	
	private User user;
	
	
	public UserExtraInformation(User user) {
		
		this.user=user;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority("Admin"));
	}

	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getName();
	}

}
