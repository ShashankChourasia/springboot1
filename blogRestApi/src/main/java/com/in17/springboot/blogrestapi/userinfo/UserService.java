package com.in17.springboot.blogrestapi.userinfo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.in17.springboot.blogrestapi.exception.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public String addUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRoles("ROLE_USER");	
		repository.save(user);
		return "User added successfully";
	}
	
	public int getUserByName(String name) {
		Optional<User> userOptional =repository.findByName(name);
		return userOptional.map(User::getId).orElse(null);
	}

	public Optional<User> getUserById(int id)  {
		Optional<User> user= repository.findById(id);
		if(user.isEmpty())
			throw new ResourceNotFoundException("User not found with id: " +id);
		else
			return user;
	}
}
