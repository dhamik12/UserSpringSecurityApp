package com.telusko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.model.User;
import com.telusko.repo.IUserRepo;
@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepo repo;

	@Override
	public User registerUserCredentials(User user) {
		return repo.save(user);
	
	}

}
