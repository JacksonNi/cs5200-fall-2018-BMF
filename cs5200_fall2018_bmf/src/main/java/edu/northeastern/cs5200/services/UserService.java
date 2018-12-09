package edu.northeastern.cs5200.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.repositories.UserRepository;

@RestController
public class UserService {
	
	@Autowired
	UserRepository ur;
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return ur.save(user);
	} 
	
	@PostMapping("/api/login")
	public User login(@RequestBody Player credential, HttpSession session) {
		User user = ur.findByCredentials(credential.getUsername(), credential.getPassword());
		if (user != null) {
			session.setAttribute("currentUser", user);
			return user;
		} else {
			return null;
		}
	} 
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	

}
