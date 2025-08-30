package com.telusko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.model.User;
import com.telusko.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
	
	BCryptPasswordEncoder	bcripe=new BCryptPasswordEncoder(12);
	
	@Autowired
	private IUserService service;
	
	@PostMapping("/add-user")
	public ResponseEntity<User> addNewCustomer(@RequestBody User user){
		
		String encodedPassword = bcripe.encode(user.getPassword());
		user.setPassword(encodedPassword);
		User userDetails = service.registerUserCredentials(user);
		return new ResponseEntity<User>(userDetails,HttpStatus.OK);
	}

	@GetMapping("/info")
	public ResponseEntity<String> getInfo(){
		String info="Telusko might launch GenAI Course";
		return new ResponseEntity<String>(info,HttpStatus.OK);
	}
	
	@GetMapping("/more-info")
	public ResponseEntity<String> getMoreInfo(){
		String info="Telusko has already launch SpringAI Course";
		return new ResponseEntity<String>(info,HttpStatus.OK);
	}
	

}
