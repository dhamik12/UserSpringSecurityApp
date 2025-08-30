package com.telusko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.model.User;
import com.telusko.repo.IUserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUserRepo repo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByName(username);
		if(user==null) {
			
			throw new UsernameNotFoundException("User not found 404");
		}
		return new UserExtraInformation(user);
		//return new userPrincipal(user);
		//usually we should use UserPrincipal class for describing user details. Its a convention.
	}
	


}
