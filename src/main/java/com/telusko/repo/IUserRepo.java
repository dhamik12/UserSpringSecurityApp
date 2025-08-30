package com.telusko.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.model.User;

@Repository
public interface IUserRepo extends JpaRepository<User, String> {
	
	
	User findByName(String userName);

}
