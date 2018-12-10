package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User findById(int id) {
		Optional<User> oUser = userRepo.findById(id);
		if (oUser.isPresent()) {
			return oUser.get();
		}
		return null;
	}
}
